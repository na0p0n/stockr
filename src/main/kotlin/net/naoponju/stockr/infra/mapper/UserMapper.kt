package net.naoponju.stockr.infra.mapper

import net.naoponju.stockr.domain.entity.User
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface UserMapper {
    @Select("""
        SELECT
          id,
          username,
          email,
          password_hash,
          role_id,
          is_active,
          created_at,
          updated_at
        FROM stockr_dev.users
        WHERE id = #{userId};
    """)
    fun findUserById(userId: Long): User?

    @Insert("""
        INSERT INTO users(
          username,
          email,
          password_hash,
          role_id,
          is_active,
          created_at,
          updated_at
        )
        VALUES (
          #{username},
          #{email},
          #{passwordHash},
          #{roleId},
          #{isActive},
          #{createdAt},
          #{updatedAt}
        );
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun createUser(user: User)

    @Update("""
        UPDATE users
          SET
            username = #{username},
            email = #{email},
            password_hash = #{passwordHash},
            role_id = #{roleId},
            is_active = #{isActive},
            created_at = #{createdAt},
            updated_at = #{updatedAt}
        WHERE id = #{id};
    """)
    fun updateUser(user: User): Int

    @Delete("""
        DELETE FROM users WHERE id = #{userId};
    """)
    fun deleteUser(userId: Long): Int
}