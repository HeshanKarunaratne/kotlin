package advanced_concepts.generics

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

class Mug<T : Beverage>(val beverage: T) {
    val temperature = beverage.idealTemperature
}

fun drink(coffee: Coffee) = println("Drinking coffee: $coffee")
fun drink(tea: Tea) = println("Drinking tea: $tea")

fun main() {
    val coffeeMug = Mug(Coffee.LIGHT_ROAST)
    drink(coffeeMug.beverage)

    val teaMug = Mug(Tea.GREEN_TEA)
    drink(teaMug.beverage)

    val mug = serve(Coffee.DARK_ROAST)
    val mug2 = Coffee.DARK_ROAST.pourIntoMug()
}

class Dish<T>(private var food: T) {
    fun replaceFood(newFood: T) {
        println("Replacing $food with $newFood")
        food = newFood
    }

    fun getFood(): T = food
}

fun <T : Beverage> serve(beverage: T): Mug<T> = Mug(beverage)
fun <T : Beverage> T.pourIntoMug() = Mug(this)