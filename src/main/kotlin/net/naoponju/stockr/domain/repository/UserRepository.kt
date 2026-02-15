package net.naoponju.stockr.domain.repository

import net.naoponju.stockr.domain.entity.User
import java.time.LocalDateTime

interface UserRepository {
    fun findById(userId: Long): User?

    fun add(user: User): User

    fun update(user: User): User

    fun deleteById(userId: Long, updatedAt: LocalDateTime, deletedAt: LocalDateTime)
}
