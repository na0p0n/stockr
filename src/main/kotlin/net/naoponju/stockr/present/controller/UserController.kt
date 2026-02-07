package net.naoponju.stockr.present.controller

import net.naoponju.stockr.application.dto.UserRegistrationRequest
import net.naoponju.stockr.application.dto.UserResponse
import net.naoponju.stockr.application.service.UserService
import net.naoponju.stockr.domain.entity.User
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/user")
abstract class UserController(
    private val userService: UserService
) {
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User {
        return userService.getUserById(id)
    }

    @PostMapping
    fun createUser(@RequestBody user: UserRegistrationRequest): UserResponse {
        return userService.signUp(user)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): UserResponse {
        return userService.updateProfile(user.copy(id = id))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }
}
