package advanced_concepts.inheritance_composition.composition

interface IVehicle {
    val speed: Double
    fun accelerate()
    fun makeEngineSound()
}

class Vehicle(private val acceleration: Double) : IVehicle {
    override var speed = 0.0; private set
    override fun accelerate() {
        speed += acceleration
    }

    override fun makeEngineSound() = println("Vrrrr...")
}

class RaceCar(vehicle: IVehicle) : IVehicle by vehicle {
    override fun makeEngineSound() = println("Vroom! Vroom!")
}

fun main() {
    val raceCar = RaceCar(Vehicle(2.0))
    drive(raceCar)
}

private fun drive(vehicle: IVehicle) {
    vehicle.makeEngineSound()
    vehicle.accelerate()
    vehicle.accelerate()
    println(vehicle.speed)
}