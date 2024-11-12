package advanced_concepts.generics

interface VendingPropMachine {
    val purchase: (Coin) -> Snack
}

class SimpleVendingPropMachine1 : VendingPropMachine {
    override val purchase: (Money) -> Snack = { CandyBar() }
}

