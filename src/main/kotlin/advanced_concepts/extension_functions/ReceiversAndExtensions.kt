package advanced_concepts.extension_functions

class Dog {
    fun speak() {
        println("BARK!")
    }

    fun play() {
        //an implicit receiver
        speak()
    }
}

fun singleQuotedStandalone(original: String) = "'$original'"
fun String.singleQuotedExtension() = "'$this'"

//Extension properties
val String.isLong: Boolean
    get() = length > 20

fun main() {

    val fido = Dog()
    //fido is an explicit receiver
    fido.speak()

    val title = "The Robots from Planet X3"

    // Standalone function
    val result = singleQuotedStandalone(title.removePrefix("The ")).uppercase()
    println(result)

    // Extension function
    val result2 = title.removePrefix("The Robots from ").singleQuotedExtension().uppercase()
    println(result2)

    println(title.isLong)
    println(result2.isLong)
}

