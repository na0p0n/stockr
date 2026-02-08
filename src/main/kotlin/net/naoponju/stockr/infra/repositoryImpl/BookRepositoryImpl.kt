package net.naoponju.stockr.infra.repositoryImpl

import net.naoponju.stockr.domain.entity.Book
import net.naoponju.stockr.domain.repository.BookRepository
import net.naoponju.stockr.infra.mapper.BookMapper
import java.time.LocalDateTime

class BookRepositoryImpl(
    private val bookMapper: BookMapper
): BookRepository {
    override fun findBookById(bookId: Long): Book? {
        return bookMapper.findBookById(bookId)
    }

    override fun insertBook(book: Book): Book {
        bookMapper.insert(book)

        return book
    }

    override fun updateBook(book: Book): Book {
        val updatedCount = bookMapper.update(book)
        if (updatedCount == 0) {
            throw RuntimeException("本データ削除API: ID = ${book.id} の本情報の更新に失敗しました。")
        }

        return book
    }

    override fun deleteBook(bookId: Long, deletedAt: LocalDateTime) {
        val deletedCount = bookMapper.delete(bookId, deletedAt)
        if (deletedCount == 0) {
            throw RuntimeException("本データ削除API: ID = $bookId の本データの削除に失敗しました。")
        }
    }
}