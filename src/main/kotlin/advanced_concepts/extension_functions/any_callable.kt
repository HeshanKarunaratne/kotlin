package advanced_concepts.extension_functions

fun main() {

    operator fun Any?.invoke() = println(this)

    "Hello!"()
    1()
    true()
    null()
}