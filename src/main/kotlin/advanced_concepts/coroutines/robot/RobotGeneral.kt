package advanced_concepts.coroutines.robot

import kotlinx.coroutines.delay

enum class Product(val description: String, val deliveryTime: Long) {
    DOORS("doors", 750), WINDOWS("windows", 1_250)
}

suspend fun order(item: Product): Product {
    println("ORDER EN ROUTE >>> The ${item.description} are on the way")
    delay(item.deliveryTime)
    println("ORDER DELIVERED >>> Your ${item.description} have arrived")
    return item
}

suspend fun perform(taskName: String) {
    println("STARTING TASK >>> $taskName")
    delay(1_000)
    println("FINISHED TASK >>> $taskName")
}