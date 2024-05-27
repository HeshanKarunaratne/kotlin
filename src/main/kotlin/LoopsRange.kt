/**
 * @author Heshan Karunaratne
 */
fun main() {
    for (i in 1..5) println(i)
    for (i in 1..5 step 2) println(i)
    for (i in 5 downTo 1) println(i)

    val brand = "heshan"
    for (i in brand) println(i)
}