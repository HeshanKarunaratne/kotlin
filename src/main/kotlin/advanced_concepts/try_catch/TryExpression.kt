package advanced_concepts.try_catch

import java.time.LocalDate

val dayOfWeek = try {
    LocalDate.parse("2024-01-01").dayOfWeek.value
} catch (e: IllegalArgumentException) {
    -1
}

fun main() {
    scheduleEmail(
        try {
            LocalDate.parse("2024-01-01").dayOfWeek.value
        } catch (e: IllegalArgumentException) {
            -1
        }
    )
}

fun scheduleEmail(i: Int) {
    println(i)
}

fun dayOfWeek(dateString: String) = try {
    LocalDate.parse(dateString).dayOfWeek.value
} catch (e: IllegalArgumentException) {
    -1
}

