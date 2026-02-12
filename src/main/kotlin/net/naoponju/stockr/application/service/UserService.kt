package net.naoponju.stockr.application.service

import java.time.LocalDateTime
import net.naoponju.stockr.application.dto.UserRegistrationRequest
import net.naoponju.stockr.application.dto.UserResponse
import net.naoponju.stockr.domain.entity.User
import net.naoponju.stockr.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional(readOnly = true)
    fun getUserById(id: Long): User {
        return userRepository.findById(id)
            ?: throw NoSuchElementException("ユーザー情報取得API: ユーザー(ID: $id)が見つかりません。")
    }

    @Transactional
    fun createUser(request: UserRegistrationRequest): UserResponse {
        val nowTime = LocalDateTime.now()
        val newUser =
            User(
                id = null,
                username = request.username,
                email = request.email,
                passwordHash = passwordEncoder.encode(request.password),
                roleId = 1,
                isActive = true,
                createdAt = nowTime,
                updatedAt = nowTime,
            )
        val createdUser = userRepository.add(newUser)
        return convertToResponse(createdUser)
    }

    @Transactional
    fun updateProfile(user: User): UserResponse {
        userRepository.findById(user.id ?: throw IllegalArgumentException("ユーザー情報更新API: IDが必要です。"))
            ?: throw NoSuchElementException("ユーザー情報更新API: 更新対象のユーザーが存在しません。")
        val updatedUser = userRepository.update(user)
        return convertToResponse(updatedUser)
    }

    @Transactional
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    fun convertToResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id!!,
            username = user.username,
            email = user.email,
            roleId = user.roleId,
            isActive = user.isActive,
            createdAt = user.createdAt,
        )
    }
}
