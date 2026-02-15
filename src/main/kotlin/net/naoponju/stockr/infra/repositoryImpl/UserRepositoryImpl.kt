package net.naoponju.stockr.infra.repositoryImpl

import net.naoponju.stockr.domain.entity.User
import net.naoponju.stockr.domain.repository.UserRepository
import net.naoponju.stockr.infra.mapper.UserMapper
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UserRepositoryImpl(
    private val userMapper: UserMapper,
) : UserRepository {
    override fun findById(userId: Long): User? {
        return userMapper.selectById(userId)
    }

    override fun add(user: User): User {
        userMapper.insert(user)

        return user
    }

    override fun update(user: User): User {
        val updatedCount = userMapper.update(user)
        if (updatedCount == 0) {
            throw RuntimeException("ユーザー情報更新API: ID = ${user.id} のユーザー情報の更新に失敗しました。")
        }
        return user
    }

    override fun deleteById(userId: Long, updatedAt: LocalDateTime, deletedAt: LocalDateTime) {
        val deletedCount = userMapper.delete(userId, updatedAt, deletedAt)
        if (deletedCount == 0) {
            throw RuntimeException("ユーザー削除API: ID = $userId のユーザーの削除に失敗しました。")
        }
    }
}
