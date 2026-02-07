package net.naoponju.stockr.domain.entity

import java.time.LocalDateTime

data class User(
    val id: Long?,
    val username: String,
    val email: String,
    val passwordHash: String,
    val roleId: Int,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
