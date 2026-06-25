package examples.part6

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExercisesTest {
    @Test
    fun `double doubles after a delay`() = runTest {
        assertEquals(4, double(2))
    }

    @Test
    fun `sumOfDoubles runs concurrently`() = runTest {
        assertEquals(10, sumOfDoubles(2, 3))
    }

    @Test
    fun `loadOrFallback uses fallback on timeout`() = runTest {
        val result = loadOrFallback {
            delay(1000)
            "slow"
        }
        assertEquals("fallback", result)
    }

    @Test
    fun `squaredList squares one to five`() = runTest {
        assertEquals(listOf(1, 4, 9, 16, 25), squaredList())
    }
}
