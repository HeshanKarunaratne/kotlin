package advanced_concepts.generics_variance

interface VendingPropMachine {
    val purchase: (Coin) -> Snack
}

class SimpleVendingPropMachine1 : VendingPropMachine {
    override val purchase: (Money) -> Snack = { CandyBar() }
}

