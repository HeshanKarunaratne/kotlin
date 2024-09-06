/**
 * @author Heshan Karunaratne
 */
fun main() {

    val names = listOf<String>("heshan", "dila", "danuwa")
    val numbers = intArrayOf(1, 3, 5, 6, 8, 12)

    for (name in names){
        val n = name.replaceFirstChar {
            it.uppercase()
        }
        println(n)
    }

    for(index in numbers.indices){
        println("$index ${numbers[index]}")
    }
}