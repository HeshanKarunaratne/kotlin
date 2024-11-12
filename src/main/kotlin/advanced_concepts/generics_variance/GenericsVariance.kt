package advanced_concepts.generics_variance

open class VendingMachine {
    open fun purchase(money: Coin): Snack = Snack().getRandomSnack()
}