package advanced_concepts.extension_functions

import java.time.LocalDate
import java.time.Month

fun main() {

    operator fun Month.contains(date: LocalDate) = (date.month == this)
    operator fun Int.contains(date: LocalDate) = (date.year == this)
    infix fun Month.of(year: Int): Pair<Month, Int> = this to year
    operator fun Pair<Month, Int>.contains(date: LocalDate) = date in first && date in second

    val date = LocalDate.parse("2016-02-15")
    println(date in Month.FEBRUARY)
    println(date in 2016)
    println(date in Month.FEBRUARY of 2016)
}
