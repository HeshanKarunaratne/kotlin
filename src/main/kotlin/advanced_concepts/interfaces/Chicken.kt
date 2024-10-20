package advanced_concepts.interfaces

fun interface Speaker {
    fun speak()
}

interface Named {
    val name: String
}

interface FarmAnimal : Speaker, Named

class Chicken(override val name: String, var numberOfEggs: Int = 5) : FarmAnimal {
    override fun speak() = println("Cluck!")
}

class Pig(override val name: String, val excitementLevel: Int) : FarmAnimal {
    override fun speak() {
        repeat(excitementLevel) {
            println("Oink!")
        }
    }
}

class Cow(override val name: String) : FarmAnimal {
    override fun speak() = println("Moo!")
}

class Farmer(override val name: String) : Named {
    fun greet(animal: FarmAnimal) {
        println("Hello, ${animal.name}!")

        val chicken: Chicken? = animal as? Chicken
        chicken?.let { println("I see you have ${it.numberOfEggs} eggs today!") }

        animal.speak()

    }
}

fun main() {
    val sue = Farmer("Sue")

    val animals: List<FarmAnimal> = listOf(
        Chicken("Henrietta", 14),
        Pig("Hamlet", 1),
        Cow("Dairy Godmother"),
    )

    animals.forEach { sue.greet(it) }

    val henrietta: Chicken = Chicken("Henrietta")
    henrietta.numberOfEggs = 1

    val henrietta2: FarmAnimal = Chicken("Henrietta")
    // This is not applicable
//    henrietta2.numberOfEggs = 1
}