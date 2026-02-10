package net.naoponju.stockr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockrApplication

fun main(args: Array<String>) {
    runApplication<StockrApplication>(*args)
}
