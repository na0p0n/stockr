package net.naoponju.stockr.infra.mapper

import net.naoponju.stockr.domain.entity.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface UserMapper {
    @Select(
        """
        SELECT
          users.id,
          username,
          email,
          password_hash,
          role_id,
          user_role.role,
          is_active,
          created_at,
          updated_at
        FROM stockr_dev.users
        JOIN stockr_dev.user_role
        ON stockr_dev.users.role_id = stockr_dev.user_role.id
        WHERE id = #{userId} AND is_active = true;
    """,
    )
    fun selectById(userId: Long): User?

    @Select(
        """
        SELECT
          users.id,
          username,
          email,
          password_hash,
          role_id,
          user_role.role,
          is_active,
          created_at,
          updated_at
        FROM stockr_dev.users
        JOIN stockr_dev.user_role
        ON stockr_dev.users.role_id = stockr_dev.user_role.id
        WHERE username = #{username} AND is_active = true;
    """,
    )
    fun selectByUsername(username: String): User?

    @Insert(
        """
        INSERT INTO stockr_dev.users(
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
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insert(user: User)

    @Update(
        """
        UPDATE stockr_dev.users
          SET
            username = #{username},
            email = #{email},
            password_hash = #{passwordHash},
            role_id = #{roleId},
            is_active = #{isActive},
            created_at = #{createdAt},
            updated_at = #{updatedAt}
        WHERE id = #{id};
    """,
    )
    fun update(user: User): Int

    @Update(
        """
        UPDATE stockr_dev.users SET
          is_active = false,
          updated_at = CURRENT_TIMESTAMP,
          deleted_at = CURRENT_TIMESTAMP
        WHERE id = #{userId};
    """,
    )
    fun delete(userId: Long): Int
}
