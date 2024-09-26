package advanced_concepts.sub_types

import advanced_concepts.generics_variance.CandyBar
import advanced_concepts.generics_variance.Coin
import advanced_concepts.generics_variance.Snack

open class VendingMachine {
    open fun purchase(money: Coin): Snack = Snack()
    open fun refund(product: Snack): Coin = Coin()
}

class CandyBarMachine : VendingMachine() {
    override fun purchase(money: Coin): Snack = CandyBar()
}

val vendingMachine: VendingMachine = CandyBarMachine()