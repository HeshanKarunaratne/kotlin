import java.time.LocalDate

fun main() {
    // Operator overloading
    operator fun LocalDate.component1() = year
    operator fun LocalDate.component2() = monthValue
    operator fun LocalDate.component3() = dayOfMonth

    val today = LocalDate.parse("2016-02-15")
    val (year, month, day) = today

    println("Year=$year; Month=$month; Day=$day")
}