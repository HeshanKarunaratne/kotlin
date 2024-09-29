package advanced_concepts.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    runBlocking {
        launch {
            println("Sledge: Suplex!")
            tagOut()
            println("Sledge: Figure-four leg")
            tagOut()
            println("Sledge: Pinning 1-2-3")
        }

        launch {
            println("Hammer: Clothesline")
            tagOut()
            println("Hammer: PileDriver")
            tagOut()
        }
    }
}

suspend fun tagOut() {
    println("Tagging out")
    yield()
}