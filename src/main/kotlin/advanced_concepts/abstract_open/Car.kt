package advanced_concepts.abstract_open

open class Car(private val acceleration: Double = 1.0) {
    protected var speed = 0.0; private set

    // Protected: A function or property marked as protected will be visible to both the current class and its subclasses, but invisible to code everywhere else
    protected open fun makeEngineSound() = println("vrrrrrrr")

    fun accelerate() {
        speed += acceleration
        makeEngineSound()
    }
}

class Clunker(acceleration: Double) : Car(acceleration) {
    override fun makeEngineSound() {
        println("putt-putt-putt")
    }
}

class Speedy(acceleration: Double) : Car(acceleration)

class MuscleCar : Car(5.0) {
    override fun makeEngineSound() = when {
        speed < 10.0 -> println("Vrooooom")
        speed < 20.0 -> println("Vrooooooooom")
        else -> println("Vrooooooooooooooooooom!")
    }
}

fun main() {
    val clunker = Clunker(0.25)
    clunker.accelerate()

    val speedy = Speedy(1.5)
    speedy.accelerate()

    val myCar = Car(1.0)
    myCar.accelerate()
}