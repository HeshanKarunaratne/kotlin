package advanced_concepts.data_classes

class Child(val name: String)

val fiona1 = Child("Fiona")
val fiona2 = Child("Fiona")

class DollarBill(val amount: Int) {
    override fun equals(other: Any?) = if (other is DollarBill) amount.equals(other.amount) else false

    override fun hashCode() = amount.hashCode()

    override fun toString(): String {
        return "DollarBill(amount=$amount)"
    }
}

data class DollarBill2(val amount: Int)

val bill1 = DollarBill(5)
val bill2 = DollarBill(5)

fun main() {
    println(fiona1 == fiona2) // false

    val fiona2 = fiona1
    println(fiona1 == fiona2) // true

    println(bill1 == bill2)  // true - checks the equal() function
    println(bill1 === bill2) // false - checks the same object instance

    val denominations = mutableSetOf<DollarBill>()

    denominations.add(DollarBill(1))
    denominations.add(DollarBill(2))
    denominations.add(DollarBill(5))
    denominations.add(DollarBill(5))
    println(denominations.size)
    println(DollarBill(1))

    println("--------Data Class-------")

    val denominations2 = mutableSetOf<DollarBill2>()
    denominations2.add(DollarBill2(1))
    denominations2.add(DollarBill2(2))
    denominations2.add(DollarBill2(5))
    denominations2.add(DollarBill2(5))
    println(denominations2.size)
    println(DollarBill2(1))

    val dollarBill2 = DollarBill2(100)
    val dollarBill2Updated = dollarBill2.copy(amount = 120)
    println(dollarBill2Updated)

    val (amount) = dollarBill2
    println(amount)
}