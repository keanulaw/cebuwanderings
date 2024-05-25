package com.example.cebuwanderingsagain.sampledata

data class Comment(
    val userId: String = "",
    val userName: String = "",
    val message: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
