package advanced_concepts.coroutines.robot

suspend fun main() {
    val start = System.nanoTime()
    val windows = order(Product.WINDOWS)
    val doors = order(Product.DOORS)
    perform("laying bricks")
    perform("installing ${windows.description}")
    perform("installing ${doors.description}")
    val end = System.nanoTime()
    println("finished in ${(end - start) / 1_000_000} ms")
}
