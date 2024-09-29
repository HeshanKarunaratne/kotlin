package advanced_concepts.coroutines.robot

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    val start = System.nanoTime()
    runBlocking {
        val windows = async { order(Product.WINDOWS) }
        val doors = async { order(Product.DOORS) }
        launch {
            perform("laying bricks")
            perform("installing ${windows.await().description}")
            perform("installing ${doors.await().description}")
        }
    }
    val end = System.nanoTime()
    println("finished in ${(end - start) / 1_000_000} ms")
}
