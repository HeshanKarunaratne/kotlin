package advanced_concepts.try_catch

import java.time.LocalDate

fun main() {
    val result: Result<LocalDate> = runCatching { LocalDate.parse("2024-01-01") }

    result.onSuccess { date -> println(date.dayOfWeek) }
    result.onFailure { ex -> println("Unable to parse. ${ex.message}") }
}