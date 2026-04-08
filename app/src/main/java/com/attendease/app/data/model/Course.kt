package com.attendease.app.data.model

enum class CourseStatus { UPCOMING, LIVE, DONE }

data class Course(
    val id: Int,
    val firestoreId: String,
    val name: String,
    val teacherName: String,
    val room: String,
    val time: String,
    val totalStudents: Int,
    val presentCount: Int,
    val status: CourseStatus
)