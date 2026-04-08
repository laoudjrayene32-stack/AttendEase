package com.attendease.app.utils

object Routes {

    const val LOGIN = "login"

    const val TEACHER_DASH = "teacher_dashboard"
    const val TEACHER_STATS = "teacher_stats"

    const val ADMIN_TEACHERS = "admin_teachers"
    const val ADMIN_CLASSES = "admin_classes"
    const val ADMIN_STUDENTS = "admin_students"
    const val ADMIN_STATS = "admin_stats"

    const val SCANNER =
        "scanner/{courseFirestoreId}/{courseName}/{courseRoom}/{courseTime}"

    fun scannerRoute(
        courseFirestoreId: String,
        courseName: String,
        courseRoom: String,
        courseTime: String
    ): String {
        return "scanner/$courseFirestoreId/$courseName/$courseRoom/$courseTime"
    }
}