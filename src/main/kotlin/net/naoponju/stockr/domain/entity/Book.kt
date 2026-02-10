package net.naoponju.stockr.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class Book(
    val id: Long?,
    val title: String,
    val author: String?,
    val publisher: String?,
    val publishDate: LocalDate?,
    val isbn: String,
    val price: BigDecimal?,
    val thumbnailUrl: String?,
    val description: String?,
    val deletedAt: LocalDateTime?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
