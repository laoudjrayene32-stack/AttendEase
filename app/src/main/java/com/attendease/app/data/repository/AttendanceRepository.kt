package com.attendease.app.data.repository

import com.attendease.app.data.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AttendanceRepository @Inject constructor() {

    private val auth = FirebaseAuth.getInstance()
    private val db   = FirebaseFirestore.getInstance()

    // ─── LOGIN ─────────────────────────────────────────────
    suspend fun login(email: String, password: String): User? {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val uid    = result.user?.uid ?: return null
            val doc    = db.collection("users").document(uid).get().await()
            doc.toObject(UserFirestore::class.java)?.toUser()
        } catch (e: Exception) {
            null
        }
    }

    // ─── COURSES FOR TEACHER ─────────────────────────────
    fun getCoursesForTeacher(teacherId: String): Flow<List<Course>> = callbackFlow {
        val listener = db.collection("courses")
            .whereEqualTo("teacherId", teacherId)
            .addSnapshotListener { snap, _ ->
                val list = snap?.documents
                    ?.mapNotNull {
                        it.toObject(CourseFirestore::class.java)
                            ?.toCourse(it.id)
                    }
                    ?: emptyList()

                trySend(list)
            }

        awaitClose { listener.remove() }
    }

    // ─── ALL COURSES ─────────────────────────────
    fun getAllCourses(): Flow<List<Course>> = callbackFlow {
        val listener = db.collection("courses")
            .addSnapshotListener { snap, _ ->
                val list = snap?.documents
                    ?.mapNotNull {
                        it.toObject(CourseFirestore::class.java)
                            ?.toCourse(it.id)
                    }
                    ?: emptyList()

                trySend(list)
            }

        awaitClose { listener.remove() }
    }

    // ─── ALL USERS ─────────────────────────────
    fun getAllUsers(): Flow<List<User>> = callbackFlow {
        val listener = db.collection("users")
            .addSnapshotListener { snap, _ ->
                val list = snap?.documents
                    ?.mapNotNull {
                        it.toObject(UserFirestore::class.java)
                            ?.toUser()
                    }
                    ?: emptyList()

                trySend(list)
            }

        awaitClose { listener.remove() }
    }

    // ─── PROCESS QR SCAN ─────────────────────────────
    suspend fun processQrScan(qrCode: String, courseFirestoreId: String): QrScanResult {

        val studentSnap = db.collection("students")
            .whereEqualTo("qrCode", qrCode)
            .get().await()

        if (studentSnap.isEmpty) {
            return QrScanResult(
                studentId   = "",
                studentName = "",
                studentCode = "",
                isEnrolled  = false,
                message     = "الطالب غير موجود في النظام"
            )
        }

        val studentDoc  = studentSnap.documents.first()
        val student     = studentDoc.toObject(StudentFirestore::class.java)!!
        val studentId   = studentDoc.id

        if (!student.enrolledCourses.contains(courseFirestoreId)) {
            return QrScanResult(
                studentId   = studentId,
                studentName = student.name,
                studentCode = student.studentCode,
                isEnrolled  = false,
                message     = "${student.name} غير مسجل في هذه الحصة"
            )
        }

        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
            .format(java.util.Date())

        val existingSnap = db.collection("attendance")
            .whereEqualTo("courseId",  courseFirestoreId)
            .whereEqualTo("studentId", studentId)
            .whereEqualTo("date",      today)
            .get().await()

        if (!existingSnap.isEmpty) {
            return QrScanResult(
                studentId        = studentId,
                studentName      = student.name,
                studentCode      = student.studentCode,
                isEnrolled       = true,
                isAlreadyScanned = true,
                message          = "${student.name} سبق تسجيل حضوره"
            )
        }

        val record = hashMapOf(
            "courseId"    to courseFirestoreId,
            "studentId"   to studentId,
            "studentName" to student.name,
            "date"        to today,
            "isPresent"   to true,
            "scannedAt"   to com.google.firebase.Timestamp.now()
        )

        db.collection("attendance").add(record).await()

        db.collection("courses").document(courseFirestoreId)
            .update("presentCount", com.google.firebase.firestore.FieldValue.increment(1))
            .await()

        return QrScanResult(
            studentId   = studentId,
            studentName = student.name,
            studentCode = student.studentCode,
            isEnrolled  = true,
            message     = "تم تسجيل حضور ${student.name} ✓"
        )
    }

    // ─── ATTENDANCE STATS ─────────────────────────────
    suspend fun getAttendanceStats(courseFirestoreId: String): Map<String, Int> {
        val snap = db.collection("attendance")
            .whereEqualTo("courseId", courseFirestoreId)
            .whereEqualTo("isPresent", true)
            .get().await()

        return mapOf("present" to snap.size())
    }
}