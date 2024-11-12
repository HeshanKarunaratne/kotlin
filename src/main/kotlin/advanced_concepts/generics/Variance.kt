package advanced_concepts.generics

open class Product
open class Snack : Product()
class CandyBar : Snack()

open class Money
open class Coin : Money()
class Quarter : Coin()

fun interface VendingMachine {
    fun purchase(money: Coin): Snack
}

class SimpleVendingMachine1 : VendingMachine {
    override fun purchase(money: Coin): Snack = Snack()
}

class SimpleVendingMachine2 : VendingMachine {
    override fun purchase(money: Coin): CandyBar = CandyBar()
}

class SimpleVendingMachine3 : VendingMachine {
    private fun purchase(money: Money): CandyBar = CandyBar()
    override fun purchase(coin: Coin): Snack = purchase(coin as Money)
}