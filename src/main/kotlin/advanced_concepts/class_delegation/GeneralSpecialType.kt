package advanced_concepts.class_delegation

fun interface Eater {
    fun eat()
}

class Muncher(private val food: String) : Eater {
    override fun eat() = println("Eating $food - munch, munch, munch!")
}

class Scarfer(private val food: String) : Eater {
    override fun eat() = println("Scarfing down $food - NOM NOM NOM!!!")
}

class Cow : Eater by Muncher("grass")
class Chicken : Eater by Muncher("bugs")
class Pig : Eater by Scarfer("corn")

fun main() {
    Cow().eat()
    Chicken().eat()
    Pig().eat()
}