package advanced_concepts.reified

inline fun <reified T> secondItemHasType(list: List<*>): Boolean {
    return list[1] is T
}

fun main() {
    val list: List<Any> = listOf(1, "two", 3)
    val secondItemIsString = secondItemHasType<String>(list)
    println(secondItemIsString)
}