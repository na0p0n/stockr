package net.naoponju.stockr.present.presenter.advice

import net.naoponju.stockr.common.LoggerDelegate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.security.access.AccessDeniedException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger by LoggerDelegate

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> {
        logger.warn("${e.message}")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("対象のデータが見つかりませんでした。")
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralError(e: Exception): ResponseEntity<String> {
        logger.error("予期せぬエラーが発生しました: ${e.message}")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("予期せぬエラーが発生しました。")
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(e: AccessDeniedException): ResponseEntity<String> {
        logger.error("${e.message}")
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("権限がありません。")
    }
}
