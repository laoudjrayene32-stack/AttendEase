package com.attendease.app.data.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "" // نخلوها String باش ما يكون حتى مشكل
)