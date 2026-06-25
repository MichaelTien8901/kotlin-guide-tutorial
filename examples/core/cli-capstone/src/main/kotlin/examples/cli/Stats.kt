package examples.cli

/** Statistics about a body of text. */
data class Stats(
    val lines: Int,
    val words: Int,
    val chars: Int,
    val topWords: List<Pair<String, Int>>,
)

/**
 * Computes line/word/char counts and the most frequent words.
 * Pure function — easy to unit test (see StatsTest).
 */
fun textStats(text: String, topN: Int = 5): Stats {
    val lineList = if (text.isEmpty()) emptyList() else text.trimEnd('\n').split("\n")

    val words = text.split(Regex("\\s+")).filter { it.isNotBlank() }

    val frequencies = words
        .map { it.lowercase().trim('.', ',', '!', '?', ';', ':', '"', '\'') }
        .filter { it.isNotEmpty() }
        .groupingBy { it }
        .eachCount()

    val top = frequencies.entries
        .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
        .take(topN)
        .map { it.key to it.value }

    return Stats(lines = lineList.size, words = words.size, chars = text.length, topWords = top)
}
