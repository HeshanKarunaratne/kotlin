package advanced_concepts.clocks

import kotlin.time.TimeSource.Monotonic.ValueTimeMark
import kotlin.time.TimeSource.Monotonic.markNow

data class Record(val label: String, val mark: ValueTimeMark)

infix fun String.at(mark: ValueTimeMark) = Record(this, mark)
val records = mutableListOf<Record>()

fun main() {

    records.add("Started" at markNow())

    Thread.sleep(100)
    records.add("Initialized" at markNow())

    Thread.sleep(200)
    records.add("Allocated" at markNow())

    Thread.sleep(100)
    records.add("Ended" at markNow())

    records.windowed(2) { (previous, current) ->
        println("${current.label} in ${(current.mark - previous.mark)}")
    }

    println("Total time: ${records.last().mark - records.first().mark}")
}