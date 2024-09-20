package advanced_concepts.intersection_types

import java.net.URL
import java.time.LocalDate


interface Named {
    val name: String
}

interface Addressable {
    val url: URL
}

class User(override val name: String, override val url: URL, val lastAccess: LocalDate) : Named, Addressable
class Product(override val name: String, override val url: URL, val price: Int) : Named, Addressable

val exampleUser = User(
    url = URL("http://example.com/u/12345"), name = "Farmer Sue", lastAccess = LocalDate.now()
)

val exampleProduct = Product(
    url = URL("http://example.com/u/67890"), name = "Farmer Karu", price = 12_99
)

fun <T> process(item: T) where T : Named, T : Addressable {
    println("Refreshing data for ${item.name} at ${item.url}")
}

fun main() {
    process(exampleUser)
    process(exampleProduct)

}