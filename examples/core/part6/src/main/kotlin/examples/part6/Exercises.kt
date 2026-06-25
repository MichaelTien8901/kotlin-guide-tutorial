package examples.part6

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withTimeoutOrNull

// Reference solutions for the Part 6 (Coroutines & Concurrency) exercises.

/** Suspend functions: delay, then double. */
suspend fun double(x: Int): Int {
    delay(100)
    return x * 2
}

/** Builders: compute two doubles concurrently and sum them. */
suspend fun sumOfDoubles(a: Int, b: Int): Int = coroutineScope {
    val da = async { double(a) }
    val db = async { double(b) }
    da.await() + db.await()
}

/** Structured concurrency: fall back if the work takes too long. */
suspend fun loadOrFallback(slow: suspend () -> String): String =
    withTimeoutOrNull(50) { slow() } ?: "fallback"

/** Flow: square 1..5 and collect to a list. */
suspend fun squaredList(): List<Int> =
    (1..5).asFlow().map { it * it }.toList()
