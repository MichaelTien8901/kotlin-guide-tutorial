package examples.part2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.PI

class ExercisesTest {
    @Test
    fun `circleArea computes pi r squared`() {
        assertEquals(PI * 4, circleArea(2.0), 1e-9)
    }

    @Test
    fun `initials uppercases first letters`() {
        assertEquals("AL", initials("ada lovelace"))
    }

    @Test
    fun `lengthOrZero handles null and non-null`() {
        assertEquals(0, lengthOrZero(null))
        assertEquals(3, lengthOrZero("abc"))
    }

    @Test
    fun `fizzbuzz classifies numbers`() {
        assertEquals("FizzBuzz", fizzbuzz(15))
        assertEquals("Fizz", fizzbuzz(9))
        assertEquals("Buzz", fizzbuzz(10))
        assertEquals("7", fizzbuzz(7))
    }

    @Test
    fun `factorial computes`() {
        assertEquals(1L, factorial(0))
        assertEquals(120L, factorial(5))
    }
}
