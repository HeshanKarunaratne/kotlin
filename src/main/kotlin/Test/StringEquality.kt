/**
 * @author Heshan Karunaratne
 */
fun main() {
    val name1 = "heshan"
    val name2 = String("heshan".toCharArray())

    println("== ${name1 == name2}")
    println(".equals ${name1.equals(name2)}")
    println("=== ${name1 === name2}")
}