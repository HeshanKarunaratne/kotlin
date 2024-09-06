/**
 * @author Heshan Karunaratne
 */
fun main() {
    val name = "heShan"
    println(name)
    println(name.uppercase())
    println(name.lowercase())
    println(name.length)
    println(name[0])
    println(name[5])

    val email = """
        Hello $name %s
             How are you !!!!      
         Haha
    """.trimIndent()
    println(email.format("karu"))
}
