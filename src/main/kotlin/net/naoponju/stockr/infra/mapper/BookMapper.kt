package net.naoponju.stockr.infra.mapper

import net.naoponju.stockr.domain.entity.Book
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import java.time.LocalDateTime

@Mapper
interface BookMapper {
    @Select("""
        SELECT
          id,
          title,
          author,
          publisher,
          publish_date,
          isbn,
          price,
          thumbnail_url,
          description,
          deleted_at,
          created_at,
          updated_at
        FROM books
        WHERE id = #{bookId}
    """
    )
    fun findBookById(bookId: Long): Book

    @Insert("""
        INSERT INTO books (
          title,
          author,
          publisher,
          publish_date,
          isbn,
          price,
          thumbnail_url,
          description,
          deleted_at,
          created_at,
          updated_at
        )
        VALUES (
          #{title},
          #{author},
          #{publisher},
          #{publishDate},
          #{isbn},
          #{price},
          #{thumbnailUrl},
          #{description},
          #{createdAt},
          #{updatedAt}
        )
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insert(book: Book)

    @Update("""
        UPDATE books SET
          title = #{title},
          author = #{author},
          publisher = #{publisher},
          publish_date = #{publishDate},
          isbn = #{isbn},
          price = #{price},
          thumbnail_url = #{thumbnailUrl},
          description = #{description},
          updated_at = #{updatedAt}
        WHERE id = #{id};
    """)
    fun update(book: Book): Int

    // deleted_atにLocalDateTimeを設定することで削除判定
    @Update("""
        UPDATE books SET
          deleted_at = #{deletedAt}
        WHERE id = #{id};
    """)
    fun delete(bookId: Long, deletedAt: LocalDateTime): Int
}