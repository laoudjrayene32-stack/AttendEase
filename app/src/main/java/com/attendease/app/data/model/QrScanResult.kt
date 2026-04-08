package com.attendease.app.data.model

data class QrScanResult(
    val studentId: String,
    val studentName: String,
    val studentCode: String,
    val isEnrolled: Boolean,
    val isAlreadyScanned: Boolean = false,
    val message: String = ""
)