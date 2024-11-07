package advanced_concepts.generics_variance

sealed interface Beverage {
    val idealTemperature: Int
}

enum class Tea : Beverage {
    GREEN_TEA, BLACK_TEA, RED_TEA;

    override val idealTemperature = 140
}

enum class Coffee : Beverage {
    LIGHT_ROAST, MEDIUM_ROAST, DARK_ROAST;

    override val idealTemperature = 135
}

class Mug<T>(val beverage: T) {
    val temperature = beverage.idealTemperature
}

fun drink(coffee: Coffee) = println("Drinking coffee: $coffee")
fun drink(tea: Tea) = println("Drinking tea: $tea")

fun main() {
    val coffeeMug = Mug(Coffee.LIGHT_ROAST)
    drink(coffeeMug.beverage)

    val teaMug = Mug(Tea.GREEN_TEA)
    drink(teaMug.beverage)

}