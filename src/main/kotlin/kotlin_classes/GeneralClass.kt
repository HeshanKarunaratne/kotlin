package kotlin_classes

import kotlin.math.PI

class Circle(private val radius: Double) {
    fun area() = 2 * PI * radius
}

class Square(private val size: Double) {
    fun area() = size * size
}

fun main() {
    val circle = Circle(10.0)
    println(circle.area())

    val square = Square(10.0)
    println(square.area())
}