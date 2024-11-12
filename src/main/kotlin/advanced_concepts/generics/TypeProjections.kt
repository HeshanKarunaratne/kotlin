package advanced_concepts.generics

class VendingTypeProjectionMachine<T : Snack>(private val product: T) {
    fun purchase(money: Money): T = product
    fun refund(product: T): Money = Coin()
}

val snackMachine: VendingTypeProjectionMachine<Snack> = VendingTypeProjectionMachine(CandyBar())
val candyBarMachine: VendingTypeProjectionMachine<in CandyBar> = snackMachine

//val result: Any? = candyBarMachine.purchase(Coin())