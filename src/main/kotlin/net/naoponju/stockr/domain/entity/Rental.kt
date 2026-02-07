package net.naoponju.stockr.domain.entity

import java.time.LocalDate
import java.time.LocalDateTime

data class Rental(
    val id: Long?,
    val userId: Long,
    val bookId: Long,
    val rentalDate: LocalDate,
    val dueDate: LocalDate,
    val returnDate: LocalDate?,
    val statusId: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
