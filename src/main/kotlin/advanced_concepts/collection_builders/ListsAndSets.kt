package advanced_concepts.collection_builders

fun main() {
    var immutableList: List<String> = listOf("a", "b", "c")
    immutableList = immutableList + "d" - "a"
    println(immutableList)

    val mutableList: MutableList<String> = mutableListOf("a", "b", "c")
    mutableList.add("d")
    mutableList.remove("a")
    println(mutableList)

}