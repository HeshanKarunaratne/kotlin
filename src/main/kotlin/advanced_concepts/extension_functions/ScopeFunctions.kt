package advanced_concepts.extension_functions

val pi = 3.14

fun main() {
    val radii = listOf(1.0, 2.0, 3.0)

    class Circle(
        val radius: Double
    ) {
        fun circumference(): Double {
            val multiplier = 2.0
            val diameter = radius * multiplier
            return multiplier * pi * radius
        }

        val area = pi * radius * radius
    }

    val areas = radii.map {
        Circle(it).area
    }

    val book = Book("main title")
    book.printChapter(1)
}

class Book(val title: String) {
    fun printChapter(number: Int) {
        println("Chapter $number $title")
    }
}