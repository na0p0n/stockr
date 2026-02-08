package net.naoponju.stockr.application.dto

import java.time.LocalDateTime

data class UserRegistrationRequest(
    val username: String,
    val email: String,
    val password: String
)

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val roleId: Int,
    val isActive: Boolean,
    val createdAt: LocalDateTime
)