package com.attendease.app.data.model

// ─────────────────────────────────────────
// 🔥 USER FIRESTORE
// ─────────────────────────────────────────
data class UserFirestore(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = ""
) {
    fun toUser(): User {
        return User(
            id = id,
            name = name,
            email = email,
            role = role
        )
    }
}

// ─────────────────────────────────────────
// 🔥 STUDENT FIRESTORE
// ─────────────────────────────────────────
data class StudentFirestore(
    val name: String = "",
    val studentCode: String = "",
    val qrCode: String = "",
    val enrolledCourses: List<String> = emptyList()
)

// ─────────────────────────────────────────
// 🔥 COURSE FIRESTORE
// ─────────────────────────────────────────
data class CourseFirestore(
    val name: String = "",
    val teacherId: String = "",
    val teacherName: String = "",
    val room: String = "",
    val time: String = "",
    val totalStudents: Int = 0,
    val presentCount: Int = 0
) {
    fun toCourse(firestoreId: String): Course {
        return Course(
            id = 0,
            firestoreId = firestoreId,
            name = name,
            teacherName = teacherName,
            room = room,
            time = time,
            totalStudents = totalStudents,
            presentCount = presentCount,
            status = CourseStatus.UPCOMING
        )
    }
}