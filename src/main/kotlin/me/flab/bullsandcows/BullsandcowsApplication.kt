package me.flab.bullsandcows

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BullsandcowsApplication

fun main(args: Array<String>) {
    runApplication<BullsandcowsApplication>(*args)
}
