package net.naoponju.stockr.infra.repositoryImpl

import net.naoponju.stockr.domain.entity.Book
import net.naoponju.stockr.domain.repository.BookRepository
import net.naoponju.stockr.infra.mapper.BookMapper
import java.time.LocalDateTime

class BookRepositoryImpl(
    private val bookMapper: BookMapper
): BookRepository {
    override fun findBookById(bookId: Long): Book? {

    }

    override fun insertBook(book: Book) {
        TODO("Not yet implemented")
    }

    override fun updateBook(book: Book) {
        TODO("Not yet implemented")
    }

    override fun deleteBook(bookId: Long, deletedAt: LocalDateTime) {
        TODO("Not yet implemented")
    }
}