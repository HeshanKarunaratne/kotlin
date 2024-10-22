package advanced_concepts.class_delegation

interface KitchenStaff {
    fun prepareEntree(name: String): Entree?
    fun prepareAppetizer(name: String): Appetizer?
    fun prepareDessert(name: String): Dessert?
    fun receiveCompliment(message: String)
}

interface BarStaff {
    fun prepareBeverage(name: String): Beverage?
    fun receiveCompliment(message: String)
}

class Bartender : BarStaff {
    override fun prepareBeverage(name: String): Beverage? = when (name) {
        "Water" -> Beverage.WATER
        "Soda" -> Beverage.SODA
        "Peach Tea" -> Beverage.PEACH_ICED_TEA
        "Tea-Lemonade" -> Beverage.TEA_LEMONADE
        else -> null
    }

    override fun receiveCompliment(message: String) = println("Bartender received a compliment: $message")
}

class Chef : KitchenStaff {

    override fun prepareEntree(name: String): Entree? = when (name) {
        "Tossed Salad" -> Entree.TOSSED_SALAD
        "Salmon on Rice" -> Entree.SALMON_ON_RICE
        else -> null
    }

    override fun prepareAppetizer(name: String): Appetizer? = when (name) {
        "Potato Wedges" -> Appetizer.POTATO_WEDGES
        "Chips" -> Appetizer.CHIPS
        else -> null
    }

    override fun prepareDessert(name: String): Dessert? = when (name) {
        "Choco Fudge" -> Dessert.CHOCO_FUDGE
        "Caramel" -> Dessert.CARAMEL
        else -> null
    }

    override fun receiveCompliment(message: String) {
        println("Chef received a compliment: $message")
    }
}

class Waiter(private val chef: Chef, private val bartender: Bartender) : KitchenStaff by chef, BarStaff by bartender {
    fun acceptPayment(money: Int) = println("Thank you for paying for your meal")

    override fun prepareEntree(name: String): Entree? =
        if (name == "Tossed Salad") Entree.TOSSED_SALAD else chef.prepareEntree(name)

    override fun receiveCompliment(message: String) = when {
        message.contains("entree")   -> chef.receiveCompliment(message)
        message.contains("beverage") -> bartender.receiveCompliment(message)
        else                         -> println("Waiter received compliment: $message")
    }
}

enum class Entree { TOSSED_SALAD, SALMON_ON_RICE }
enum class Beverage { WATER, SODA, PEACH_ICED_TEA, TEA_LEMONADE }
enum class Appetizer { POTATO_WEDGES, CHIPS }
enum class Dessert { CHOCO_FUDGE, CARAMEL }

fun main() {
    val waiter = Waiter(Chef(), Bartender())

    val beverage = waiter.prepareBeverage("Water")
    val entree = waiter.prepareEntree("Salmon on Rice")

    waiter.receiveCompliment("Salmon on rice entree was fantastic!!")
    waiter.receiveCompliment("The beverage was fantastic!!")
    waiter.receiveCompliment("The service was fantastic!")
}