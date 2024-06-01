package com.example.init

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


data class Message(
    val message: String,
    val buildNumber:String
)


@RestController
class GreetingsController(
    @Value("\${com.example.init.buildNumber}") val buildNumber: String
) {


    @GetMapping("/hello")
    fun hello(): Message = Message("Hello World", buildNumber)


}