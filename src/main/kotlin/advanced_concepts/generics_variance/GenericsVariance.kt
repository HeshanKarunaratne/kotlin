package advanced_concepts.generics_variance

open class VendingMachine {
    open val purchase: (Coin) -> Snack = { randomSnack() }
}

class CandyBarMachine : VendingMachine() {
    override val purchase: (Coin) -> CandyBar = { CandyBar() }
}

fun randomSnack(): Snack {
    return Snack()
}

class AnyMoneyVendingMachine : VendingMachine() {
    override val purchase: (Money) -> Snack = { randomSnack() }
}

fun purchaseSnackFrom(machine: VendingMachine) = machine.purchase(Dime())

fun main() {
    val candyBarMachine: CandyBarMachine = CandyBarMachine()
    val vendingMachine: VendingMachine = CandyBarMachine()

    val candyBar: CandyBar = candyBarMachine.purchase(Dime())
    val snack: Snack = vendingMachine.purchase(Dime())


    val vendingMachine2: VendingMachine = AnyMoneyVendingMachine()
    val anyMoneyMachine: AnyMoneyVendingMachine = AnyMoneyVendingMachine()

    val snack1: Snack = vendingMachine2.purchase(Dime())
    val snack2: Snack = anyMoneyMachine.purchase(Dime())
    val snack3: Snack = anyMoneyMachine.purchase(OneDollar())
}