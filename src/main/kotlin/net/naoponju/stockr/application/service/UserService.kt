package net.naoponju.stockr.application.service

import java.time.LocalDateTime
import net.naoponju.stockr.application.dto.UserRegistrationRequest
import net.naoponju.stockr.application.dto.UserResponse
import net.naoponju.stockr.application.dto.UserUpdateRequest
import net.naoponju.stockr.domain.entity.User
import net.naoponju.stockr.domain.entity.UserRole
import net.naoponju.stockr.domain.repository.UserRepository
import net.naoponju.stockr.infra.auth.StockrUserDetails
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
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
                roleId = UserRole.USER.id,
                role = UserRole.USER,
                isActive = true,
                createdAt = nowTime,
                updatedAt = nowTime,
            )
        val createdUser = userRepository.add(newUser)
        return convertToResponse(createdUser)
    }

    @Transactional
    fun updateProfile(id: Long, userRequest: UserUpdateRequest): UserResponse {
        val currentUser = userRepository.findById(id)
            ?: throw NoSuchElementException("ユーザー情報更新API: 更新対象のユーザーが存在しません。")
        val updatedUser = currentUser.copy(
            username = userRequest.username ?: currentUser.username,
            email = userRequest.email ?: currentUser.email,
            passwordHash = userRequest.password?.let { passwordEncoder.encode(it) } ?: currentUser.passwordHash,
            updatedAt = LocalDateTime.now()
        )

        val savedUser = userRepository.update(updatedUser)
        return convertToResponse(savedUser)
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

    fun userVerification(id: Long) {
        val authentication = SecurityContextHolder.getContext().authentication
        val currentUser = authentication.principal as StockrUserDetails
        val currentUserId = currentUser.user.id
        val currentUserRole = currentUser.user.role

        if (currentUserId != id && currentUserRole != UserRole.ADMIN) {
            throw AccessDeniedException("このユーザーの情報にアクセスする権限がありません。")
        }
    }
}
