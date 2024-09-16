package advanced_concepts.clocks

import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

fun main() {
    classicApproach { Thread.sleep(1000) }


    val (value, duration) = measureTimedValue { Thread.sleep(1000) }
    println("value is $value and duration is $duration")

    println(duration.inWholeSeconds)
    println(duration.toDouble(DurationUnit.MINUTES))

    val duration1 = measureTime { Thread.sleep(1000) }
    println(duration1)


    val duration3 = 534_000.seconds
    duration3.toComponents { days, hours, minutes, seconds, nanoseconds ->
        print("You still got ")
        print("$days days ")
        print("$hours hours ")
        print("and $minutes minutes ")
        println("to request a refund")
    }
}

fun classicApproach(block: () -> Unit) {
    val startTime = System.nanoTime()
    block()
    val endTime = System.nanoTime()
    val duration = (endTime - startTime) / 1_000_000
    println("Finished in $duration milliseconds")
}