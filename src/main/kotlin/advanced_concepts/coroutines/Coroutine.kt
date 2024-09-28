package advanced_concepts.coroutines


import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED

fun main() {
    var cow = 0
    println("Milking cow ${++cow}"); feedChickens.resume(Unit)
    println("Milking cow ${++cow}"); feedChickens.resume(Unit)
    println("Milking cow ${++cow}"); feedChickens.resume(Unit)
    println("Milking cow ${++cow}"); feedChickens.resume(Unit)
}

val feedChickens = suspend {
    var chicken = 0
    println("Feeding chicken ${++chicken}"); suspendCoroutine<Unit> { COROUTINE_SUSPENDED }
    println("Feeding chicken ${++chicken}"); suspendCoroutine<Unit> { COROUTINE_SUSPENDED }
    println("Feeding chicken ${++chicken}"); suspendCoroutine<Unit> { COROUTINE_SUSPENDED }
    println("Feeding chicken ${++chicken}"); suspendCoroutine { it.resume(Unit) }
}.createCoroutine(Continuation(EmptyCoroutineContext) {})

// Might work completely if we can change to below
//}.createCoroutineUnintercepted(Continuation(EmptyCoroutineContext) {})