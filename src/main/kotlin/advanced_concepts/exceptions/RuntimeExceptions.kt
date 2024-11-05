package advanced_concepts.exceptions

class HolidayException(val task: String) : Exception("'$task' is not allowed on holidays")

val ordinals = listOf("zeroth", "first", "second", "third", "fourth", "fifth")
fun ordinal(number: Int) = ordinals.get(number)

fun annc(number: Int, task: String): String {
    if ("clean" in task) throw HolidayException(task)
    val ordinal = ordinal(number)
    return "The $ordinal thing I will do is $task."
}

fun main() {
    val tasks = listOf(1 to "clean my room", 9 to "take out trash", 3 to "feed the dog")
    tasks.forEach { (number, task) ->
        try {
            println(annc(number, task))
        } catch (e: Exception) {
            println("Something went wrong! ${e.message}")
        }
    }

    // try expressions
    val words: String = try {
        annc(1, "task1")
    } catch (e: HolidayException) {
        "It's a holiday! I'm not going to ${e.task} today!"
    } catch (e: ArrayIndexOutOfBoundsException) {
        "I can't count that high!"
    }
    println(words)


    // Functional approach
    val result = runCatching { annc(1, "task2") }
    val text = result.getOrDefault("Something went wrong!")
    println(text)

}