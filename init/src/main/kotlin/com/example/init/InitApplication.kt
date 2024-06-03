package com.example.init

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class InitApplication

fun main(args: Array<String>) {
    runApplication<InitApplication>(*args)
}

@RestController
class MainController {
    @GetMapping("/")
    fun index(@RequestParam name: String?) = String.format("Hello %s", name ?: "guest")
}