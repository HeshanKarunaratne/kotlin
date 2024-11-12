package advanced_concepts.generics

class VendingStarProjectionMachine<T : Product>(private val product: T) {
    fun purchase(money: Money): T = product
    fun refund(product: T): Money = Coin()
    fun performMaintenance() = println("All tuned up")
}

fun main() {

    val snackMachine: VendingStarProjectionMachine<Snack> = VendingStarProjectionMachine(Snack())
    val candyBarMachine: VendingStarProjectionMachine<CandyBar> = VendingStarProjectionMachine(CandyBar())

    var anyMachine: VendingStarProjectionMachine<*>
    anyMachine = snackMachine
    anyMachine = candyBarMachine

    anyMachine.performMaintenance()
}