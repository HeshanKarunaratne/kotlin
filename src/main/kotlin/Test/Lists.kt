/**
 * @author Heshan Karunaratne
 */
fun main() {

    val names = listOf<String>("heshan", "dila", "danuwa") //read only
    println(names)
    println(names.size)
    println(names.first())
    println(names.last())

    val mutableNames = mutableListOf("heshan", "dila", "danuwa")
    println(mutableNames.contains("heshan"))
    println(mutableNames.indexOf("dila"))
    mutableNames.remove("dila")
    mutableNames.add("dila2")
    println(mutableNames)
}