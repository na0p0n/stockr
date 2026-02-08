package net.naoponju.stockr.domain.repository

import net.naoponju.stockr.domain.entity.Book
import java.time.LocalDateTime

interface BookRepository {
    fun findBookById(bookId: Long): Book?
    fun insertBook(book: Book): Book
    fun updateBook(book: Book): Book
    fun deleteBook(bookId: Long, deletedAt: LocalDateTime)
}