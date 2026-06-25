package examples.part4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class ExercisesTest {
    @Test
    fun `wordFrequencies counts words case-insensitively`() {
        assertEquals(mapOf("the" to 2, "cat" to 1, "sat" to 1), wordFrequencies("The cat the SAT"))
    }

    @Test
    fun `topN returns the largest n descending`() {
        assertEquals(listOf(5, 4, 3), topN(listOf(1, 5, 3, 2, 4), 3))
    }

    @Test
    fun `firstSquareOver finds the next square`() {
        assertEquals(64, firstSquareOver(50))
        assertEquals(100, firstSquareOver(81))
    }

    @Test
    fun `largest finds the maximum or null`() {
        assertEquals(3, largest(listOf(1, 3, 2)))
        assertNull(largest(emptyList<Int>()))
    }

    @Test
    fun `countOfType counts instances`() {
        assertEquals(2, listOf(1, "a", 2, "b", 3.0).countOfType<Int>())
    }
}
