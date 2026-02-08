package net.naoponju.stockr.present.controller

import net.naoponju.stockr.application.dto.UserRegistrationRequest
import net.naoponju.stockr.application.dto.UserResponse
import net.naoponju.stockr.application.service.UserService
import net.naoponju.stockr.common.LoggerDelegate
import net.naoponju.stockr.domain.entity.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<User> {
        logger.info("ApiCalled: ユーザー情報取得API: Start (検索対象ユーザーID: $id)")
        val user = userService.getUserById(id)

        logger.info("ユーザー情報取得API: Success (ID: $id)")
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(@RequestBody user: UserRegistrationRequest): ResponseEntity<UserResponse> {
        logger.info("ApiCalled: ユーザー新規登録API: Start ")

        val createdUser = userService.signUp(user)

        logger.info("ユーザー新規登録API: Success (ID: ${createdUser.id})")
        return ResponseEntity.ok(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<UserResponse> {
        logger.info("ApiCalled: ユーザー情報更新API: Start (更新対象ユーザーID: $id)")

        val updatedUser = userService.updateProfile(user.copy(id = id))

        logger.info("ユーザー情報更新API: Success (ID: $id)")
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        logger.info("ApiCalled: ユーザー消去API: Start (消去対象ユーザーID: $id")

        userService.deleteUser(id)

        logger.info("ユーザー消去API: Success (ID:${id})")
        return ResponseEntity.noContent().build()
    }

    companion object {
        private val logger by LoggerDelegate
    }

}

