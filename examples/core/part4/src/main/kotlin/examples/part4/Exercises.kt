package examples.part4

// Reference solutions for the Part 4 (Collections & Generics) exercises.

/** Collections: count occurrences of each lowercased word. */
fun wordFrequencies(text: String): Map<String, Int> =
    text.lowercase()
        .split(Regex("\\W+"))
        .filter { it.isNotEmpty() }
        .groupingBy { it }
        .eachCount()

/** Operations: the n largest numbers, descending. */
fun topN(numbers: List<Int>, n: Int): List<Int> =
    numbers.sortedDescending().take(n)

/** Sequences: first perfect square strictly greater than `limit`. */
fun firstSquareOver(limit: Int): Int =
    generateSequence(1) { it + 1 }
        .map { it * it }
        .first { it > limit }

/** Generics: maximum element, or null for an empty list. */
fun <T : Comparable<T>> largest(items: List<T>): T? = items.maxOrNull()

/** Reified types: how many elements are instances of T. */
inline fun <reified T> Iterable<*>.countOfType(): Int = count { it is T }
