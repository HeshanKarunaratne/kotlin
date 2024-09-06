/**
 * @author Heshan Karunaratne
 */
fun main() {
    val tv = SmartDevice("Samsung", 1000.0)
    tv.turnOn()
    tv.turnOff()
    println(tv)

    val phone = SmartDevice("Apple", 1200.0)
    phone.turnOn()
    phone.turnOff()


}

class SmartDevice constructor(
    private var brand: String, private var price: Double,
) {
    constructor() : this("", 0.0)
    constructor(brand: String) : this(brand, 0.0)

    fun turnOn() {
        println("$brand is switching on with price $price")
    }

    fun turnOff() {
        println("$brand is switching off")
    }

    override fun toString(): String {
        return "SmartDevice(brand='$brand', price=$price)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SmartDevice

        if (brand != other.brand) return false
        return price == other.price
    }

    override fun hashCode(): Int {
        var result = brand.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }


}