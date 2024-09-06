/**
 * @author Heshan Karunaratne
 */
fun main() {
    greet("heshan", 10)
    greet("dilan", 20)
    // named arguments
    greet(age = 30, name = "danu")
    greet("chiri")

    //
    foo(bar = {
        println("Bar as a function")
    })
    println(double(10))
}

//function as single expression
fun double(n: Int): Int = n * 2

//fun double(n: Int): Int {
//    return n * 2
//}

fun foo(bar: () -> Unit) {
    println("Bar function")
    bar()
}

// return 2 values
fun twoValues(): Pair<String, Int> {
    return "Val1" to 12
}

// return 3 values
fun threeValues(): Triple<String, Int, Int> {
    return Triple("One", 2, 3)
}

fun greet(name: String, age: Int = 50) {
    println("Hello $name $age")
}