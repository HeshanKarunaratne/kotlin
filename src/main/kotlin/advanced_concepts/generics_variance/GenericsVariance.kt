package advanced_concepts.generics_variance

class VendingMachine<T : Snack>(private val snack: T) {
    fun purchase(money: Coin): T = snack
    fun refund(snack: T): Coin = Dime()
}

fun main() {
    val candyBarMachine: VendingMachine<CandyBar> = VendingMachine(CandyBar())
    val vendingMachine: VendingMachine<Snack> = candyBarMachine
}