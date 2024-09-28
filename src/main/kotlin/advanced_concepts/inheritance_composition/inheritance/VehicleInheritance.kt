package advanced_concepts.inheritance_composition.inheritance

open class Vehicle(private val acceleration: Double) {
    var speed = 0.0; private set
    fun accelerate() {
        speed += acceleration
    }

    open fun makeEngineSound() = println("Vrrrr...")
}

class RaceCar(acceleration: Double) : Vehicle(acceleration) {
    override fun makeEngineSound() = println("Vroom! Vroom!")
}

fun main() {
    val raceCar = RaceCar(2.0)
    drive(raceCar)
}

private fun drive(vehicle: Vehicle) {
    vehicle.makeEngineSound()
    vehicle.accelerate()
    vehicle.accelerate()
    println(vehicle.speed)
}