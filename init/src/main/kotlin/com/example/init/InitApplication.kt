package com.example.init

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InitApplication

fun main(args: Array<String>) {
    runApplication<InitApplication>(*args)
}
