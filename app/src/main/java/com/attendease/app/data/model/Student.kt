package com.attendease.app.data.model

data class Student(
    val id: Int,
    val name: String,
    val studentId: String,
    val courseId: Int,
    val isPresent: Boolean = false
)

