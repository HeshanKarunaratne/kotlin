/**
 * @author Heshan Karunaratne
 */
fun main() {
    val gender = "F"

    val g = when (gender) {
        "F" -> "FEMALE"
        "M" -> "MALE"
        else -> "Unknown"
    }
    println(g)

    val n1 = 20
    val n2 = 30

    val c = when {
        (n1 >= 10 && n2 < 40) -> "CASE1"
        (n2 >= 10) -> "CASE2"
        else -> "CASE3"
    }
    println(c)

    val age = 18
    val r = when (age) {
        in 13..19 -> "Teen"
        !in 0..12 -> "Minor"
        else -> "Older"
    }
    println(r)
}