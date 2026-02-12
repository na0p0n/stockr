package net.naoponju.stockr.domain.entity

import java.time.LocalDateTime

data class User(
    val id: Long?,
    val username: String,
    val email: String,
    val passwordHash: String,
    val roleId: Int,
    val role: UserRole,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

enum class UserRole( val id: Int) {
    USER(1),
    ADMIN(2),
    STAFF(3);

    companion object {
        fun fromId(id: Int): UserRole {
            return UserRole.entries.find { it.id == id }
                ?: throw IllegalArgumentException("不明なロールID: $id")
        }
    }
}
