package advanced_concepts.extension_functions

import java.util.*
import kotlin.math.pow

fun main() {
    index()
    doubleCheck()
    mapCheck()
}

fun index() {
    operator fun Int.get(digit: Int): Int = div(10.0.pow(digit.toDouble())).rem(10.0).toInt()

    val number = 597
    println(number[0])
    println(number[1])
    println(number[2])

}

fun doubleCheck() {

    val double: (Int) -> Int = { it * 2 }
    val result = double(12)
    println(result)

    operator fun <T, R> ((T) -> R).get(param: T): R = this(param)
    val result2 = double[12]
    println(result2)

    operator fun <T, R> ((T) -> R).invoke(paramProvider: () -> (T)): R = this(paramProvider())
    val result3 = double { 12 }
    println(result3)
}

fun mapCheck() {

    operator fun <T, R> TreeMap<T, R>.get(index: Int): R? = this.values.elementAt(index)

    val map = TreeMap<String, String>()
    map["b"] = "bravo"
    map["a"] = "alpha"
    map["d"] = "delta"
    map["e"] = "echo"
    map["c"] = "charlie"

    println(map["d"])
    println(map[3])
}