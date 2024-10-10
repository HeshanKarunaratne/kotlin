package advanced_concepts.lambdas

fun calculateTotal(initialPrice: Double, applyDiscount: (Double) -> Double): Double {
    return applyDiscount(initialPrice) * 1.09
}

fun discountFiveDollars(price: Double): Double = price - 5.0
val discountFiveDollars2: (Double) -> Double = { it - 5.0 }

fun discountTenPercent(price: Double): Double = price * 0.9
val discountTenPercents: (Double) -> Double = { it * 0.9 }

fun noDiscount(price: Double): Double = price
val noDiscount2: (Double) -> Double = { it }

fun discountForCouponCode(couponCode: String): (Double) -> Double = when (couponCode) {
    "FIVE_BUCKS" -> dollarAmountDiscount(5.0)
    "NINE_BUCKS" -> dollarAmountDiscount(9.0)
    "TAKE_10"    -> percentageDiscount(0.10)
    "TAKE_15"    -> percentageDiscount(0.15)
    else         -> { price -> price }
}

fun dollarAmountDiscount(dollarsOff: Double): (Double) -> Double =
    { price -> price - dollarsOff }

fun percentageDiscount(percentageOff: Double): (Double) -> Double {
    val multiplier = 1.0 - percentageOff
    return { price -> price * multiplier }
}

fun main() {
    //Trailing lambda syntax
    val withFiveDollarsOff = calculateTotal(20.0) { price -> price - 5.0 }
    val withNineDollarsOff = calculateTotal(20.0, discountForCouponCode("NINE_BUCKS"))
    val withTenPercentOff = calculateTotal(20.0) { price -> price * 0.9 }
    val fullPrice = calculateTotal(20.0) { price -> price }
}