package examples.cli

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatsTest {
    @Test
    fun `counts lines and words`() {
        val stats = textStats("the cat sat\nthe cat\n", topN = 2)
        assertEquals(2, stats.lines)
        assertEquals(5, stats.words)
    }

    @Test
    fun `ranks top words by frequency then alphabetically`() {
        val stats = textStats("the cat sat the cat", topN = 2)
        assertEquals(listOf("cat" to 2, "the" to 2), stats.topWords)
    }

    @Test
    fun `strips punctuation when counting words`() {
        val stats = textStats("Hello, hello! World.", topN = 1)
        assertEquals(listOf("hello" to 2), stats.topWords)
    }

    @Test
    fun `handles empty input`() {
        val stats = textStats("")
        assertEquals(0, stats.lines)
        assertEquals(0, stats.words)
        assertEquals(emptyList<Pair<String, Int>>(), stats.topWords)
    }
}
