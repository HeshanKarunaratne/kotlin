package advanced_concepts.clocks


import kotlin.time.Duration
import kotlin.time.TimeSource.Monotonic.ValueTimeMark
import kotlin.time.TimeSource.Monotonic.markNow


class StopWatchPlugin {
    private var startTime: ValueTimeMark? = null
    fun beforeOperation() {
        startTime = markNow()
    }

    fun afterOperation() {
        val endTime: ValueTimeMark = markNow()
        val duration: Duration = endTime - startTime!!
        println("Operation took ${duration.inWholeMilliseconds}ms.")
    }
}