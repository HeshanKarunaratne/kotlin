package advanced_concepts.coroutines

fun main() {
    var cow = 0
    println("Milking cow ${++cow}")
    println("Milking cow ${++cow}")
    feedChicken()
    println("Milking cow ${++cow}")
    println("Milking cow ${++cow}")
}

fun feedChicken() {
    var chicken = 0
    println("Feeding chicken ${++chicken}")
    println("Feeding chicken ${++chicken}")
    println("Feeding chicken ${++chicken}")
    println("Feeding chicken ${++chicken}")
}