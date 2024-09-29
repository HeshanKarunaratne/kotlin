package advanced_concepts.patterns.decorator

import java.time.Clock
import java.util.*

fun interface Logger {
    fun log(message: String)
}

val consoleLogger = Logger { println(it) }
fun Logger.withUniqueId() = Logger { log("${UUID.randomUUID()} $it") }
fun Logger.withThreadName() = Logger { log("$it (on ${Thread.currentThread().name} thread)") }
fun Logger.withDateTime(clock: Clock = Clock.systemUTC()) = Logger { log("[${clock.instant()}] $it") }

fun main() {
    val consoleLogger = consoleLogger.withDateTime().withThreadName().withUniqueId()
    consoleLogger.log("Application initialized")
}