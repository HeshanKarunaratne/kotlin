package advanced_concepts.coroutines.robot

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val start = System.nanoTime()
    runBlocking {
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) { order(Product.DOORS) }
        launch(Dispatchers.Default) {
            perform("laying bricks")
            launch { perform("installing ${windows.await().description}") }
            launch { perform("installing ${doors.await().description}") }
        }
    }
    val end = System.nanoTime()
    println("finished in ${(end - start) / 1_000_000} ms")
}