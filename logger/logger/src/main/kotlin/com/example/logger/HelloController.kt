package com.example.logger

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.log


@RestController
class HelloController {

    val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/hello")
    fun hello(): String {

        logger.trace("Trace")
        logger.debug("Debug")

        logger.info("Info")
        logger.warn("Warn")
        logger.error("Error")

        try {
            val x = 1/0
        }catch (e:Throwable){
            logger.error("ERR failed to compute x", e)
        }

        return "Hello World!!"
    }

}