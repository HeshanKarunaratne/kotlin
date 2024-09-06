/**
 * @author Heshan Karunaratne
 */
fun main() {
    val names = arrayOf<String>("Heshan", "James", "Dila")
    names[0] = "updated"
    println(names[2])
    println(names.contentToString())
    println(names.size)

    if ("James" in names) {
        println("Found")
    } else {
        println("Not found")
    }

    val arrayOfNulls = arrayOfNulls<String>(5)
    arrayOfNulls[3] = "heshan"
    println(arrayOfNulls.contentToString())

    arrayOfNulls.fill("XX")
    println(arrayOfNulls.contentToString())

}