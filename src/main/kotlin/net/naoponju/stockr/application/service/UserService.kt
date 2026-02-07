package net.naoponju.stockr.application.service

import net.naoponju.stockr.application.dto.UserRegistrationRequest
import net.naoponju.stockr.application.dto.UserResponse
import net.naoponju.stockr.domain.entity.User
import net.naoponju.stockr.domain.repository.UserRepository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

class UserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getUserById(id: Long): User {
        return userRepository.findById(id)
            ?: throw NoSuchElementException("ユーザー(ID: $id)が見つかりません。")
    }

    @Transactional
    fun signUp(request: UserRegistrationRequest): UserResponse {
        val nowTime = LocalDateTime.now()
        val newUser = User(
            id = null,
            username = request.username,
            email = request.email,
            passwordHash = "hashed_${request.password}",
            roleId = 1,
            isActive = true,
            createdAt = nowTime,
            updatedAt = nowTime
        )
        val createdUser = userRepository.create(newUser)
        return convertToResponse(createdUser)
    }

    @Transactional
    fun updateProfile(user: User): UserResponse {
        userRepository.findById(user.id ?: throw IllegalArgumentException("IDが必要です。"))
            ?: throw NoSuchElementException("更新対象のユーザーが存在しません。")
        val updatedUser = userRepository.update(user)
        return convertToResponse(updatedUser)
    }

    @Transactional
    fun deleteUser(id: Long) {
        userRepository.delete(id)
    }

    fun convertToResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id!!,
            username = user.username,
            email = user.email,
            roleId = user.roleId,
            isActive = user.isActive,
            createdAt = user.createdAt
        )
    }
}