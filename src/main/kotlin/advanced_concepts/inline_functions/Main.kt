package advanced_concepts.inline_functions

fun main() {
    processRecords("Alice", "Billy", "Charlie")
}

fun processRecords(vararg records: String) {
    for (record in records) {
        executeAndMeasure(label = record, block = {
            if (record.startsWith("C")) {
                println("Saving $record")
            }
        }, onError = { ex ->
            println("Trouble saving $record - ${ex.message} - ${records.size}")
        })
    }
}

fun executeAndMeasure(label: String, block: () -> Unit, onError: (Exception) -> Unit) {
    Thread {
        try {
            val start = System.nanoTime()
            block()
            val end = System.nanoTime()
            println("Duration for $label: ${(end - start) / 1_000_000} ms")
        } catch (e: Exception) {
            println(onError)
        }
    }.start()
}