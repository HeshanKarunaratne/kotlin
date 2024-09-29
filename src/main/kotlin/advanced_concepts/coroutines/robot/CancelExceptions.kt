package advanced_concepts.coroutines.robot

import kotlinx.coroutines.*

fun main() {
    cancelCoroutine()
    cancelChildCoroutine()
    exceptionCoroutine()
}

fun cancelCoroutine() {
    val start = System.nanoTime()
    runBlocking {
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) { order(Product.DOORS) }
        launch(Dispatchers.Default) {
            perform("laying bricks")
            launch { perform("installing ${windows.await().description}") }
            launch { perform("installing ${doors.await().description}") }
        }
        cancel()
    }
    val end = System.nanoTime()
    println("finished in ${(end - start) / 1_000_000} ms")
}

fun cancelChildCoroutine() {
    val start = System.nanoTime()
    runBlocking {
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) { order(Product.DOORS).also { cancel() } }
        launch(Dispatchers.Default) {
            perform("laying bricks")
            launch { perform("installing ${windows.await().description}") }
            launch { perform("installing ${doors.await().description}") }
        }
    }
    val end = System.nanoTime()
    println("finished in ${(end - start) / 1_000_000} ms")
}

fun exceptionCoroutine() {
    val start = System.nanoTime()
    runBlocking {
        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
        val doors = async(Dispatchers.IO) {
            throw Exception("Out of money")
            order(Product.DOORS)
        }
        launch(Dispatchers.Default) {
            perform("laying bricks")
            launch { perform("installing ${windows.await().description}") }
            launch { perform("installing ${doors.await().description}") }
        }
    }
    val end = System.nanoTime()
    println("finished in ${(end - start) / 1_000_000} ms")
}