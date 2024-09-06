package kotlin_classes

enum class OrderStatus {
    PREPARING, READY_FOR_PICKUP, OUT_FOR_DELIVERY, DELIVERED
}

fun getMessage(status: OrderStatus) = when (status) {
    OrderStatus.PREPARING -> "Working on it"
    OrderStatus.READY_FOR_PICKUP -> "Ready to ship"
    OrderStatus.OUT_FOR_DELIVERY -> "Delivering today"
    OrderStatus.DELIVERED -> "Delivered"
}

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    OrderStatus.entries.forEach {
        println("${it.name}: ${it.ordinal}")
    }
    println(getMessage(status = OrderStatus.OUT_FOR_DELIVERY))
}