package net.naoponju.stockr.present.controller

import net.naoponju.stockr.application.dto.UserRegistrationRequest
import net.naoponju.stockr.application.dto.UserResponse
import net.naoponju.stockr.application.dto.UserUpdateRequest
import net.naoponju.stockr.application.service.UserService
import net.naoponju.stockr.common.LoggerDelegate
import net.naoponju.stockr.domain.entity.User
import net.naoponju.stockr.domain.entity.UserRole
import net.naoponju.stockr.infra.auth.StockrUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.access.AccessDeniedException

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: Long,
    ): ResponseEntity<User> {
        logger.info("ApiCalled: ユーザー情報取得API: Start (検索対象ユーザーID: $id)")
        val user = userService.getUserById(id)

        logger.info("ユーザー情報取得API: Success (ID: $id)")
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(
        @RequestBody user: UserRegistrationRequest,
    ): ResponseEntity<UserResponse> {
        logger.info("ApiCalled: ユーザー新規登録API: Start ")

        val createdUser = userService.createUser(user)

        logger.info("ユーザー新規登録API: Success (ID: ${createdUser.id})")
        return ResponseEntity.ok(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody user: UserUpdateRequest,
    ): ResponseEntity<UserResponse> {
        logger.info("ApiCalled: ユーザー情報更新API: Start (更新対象ユーザーID: $id)")

        verifyUserId(id)
        val updatedUser = userService.updateProfile(id, user)

        logger.info("ユーザー情報更新API: Success (ID: $id)")
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long,
    ): ResponseEntity<Unit> {
        logger.info("ApiCalled: ユーザー消去API: Start (消去対象ユーザーID: $id")

        verifyUserId(id)
        userService.deleteUser(id)

        logger.info("ユーザー消去API: Success (ID:$id)")
        return ResponseEntity.noContent().build()
    }

    fun verifyUserId( id: Long ) {
        val authentication = SecurityContextHolder.getContext().authentication
        val currentUser = authentication.principal as StockrUserDetails
        val currentUserId = currentUser.user.id
        val currentUserRole = currentUser.user.role

        if (currentUserId != id && currentUserRole ==  UserRole.USER) {
            throw AccessDeniedException("他のユーザーにアクセスする権限がありません。")
        }
    }

    companion object {
        private val logger by LoggerDelegate
    }
}
