package net.naoponju.stockr.application.dto

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class BookRegistrationRequest(
    val title: String,
    val author: String?,
    val publisher: String?,
    val publishDate: LocalDate?,
    val isbn: String,
    val price: BigDecimal?,
    val thumbnailUrl: String?,
    val description: String?,
)

data class BookResponse(
    val id: Long,
    val title: String,
    val author: String?,
    val publisher: String?,
    val publishDate: LocalDate?,
    val isbn: String,
    val price: BigDecimal?,
    val thumbnailUrl: String?,
    val description: String?,
    val createdAt: LocalDateTime
)
