package net.naoponju.stockr.infra.repositoryImpl

import net.naoponju.stockr.domain.entity.User
import net.naoponju.stockr.domain.repository.UserRepository
import net.naoponju.stockr.infra.mapper.UserMapper
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userMapper: UserMapper
): UserRepository {
    override fun findById(userId: Long): User? {
        return userMapper.findUserById(userId)
    }

    override fun create(user: User): User {
        userMapper.createUser(user)

        return user
    }

    override fun update(user: User): User {
        val updatedCount = userMapper.updateUser(user)
        if (updatedCount == 0) {
            throw RuntimeException("ID = ${user.id} のユーザー情報の更新に失敗しました。")
        }
        return user
    }

    override fun delete(id: Long) {
        userMapper.deleteUser(id)
    }
}