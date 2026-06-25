package examples.part5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ExercisesTest {
    @Test
    fun `applyTwice applies the function twice`() {
        assertEquals(8, applyTwice(2) { it * 2 })
    }

    @Test
    fun `formatUser handles null name`() {
        assertEquals("Ada (36)", formatUser("Ada", 36))
        assertEquals("unknown (0)", formatUser(null, 0))
    }

    @Test
    fun `money supports plus and comparison`() {
        assertEquals(Money(150), Money(100) + Money(50))
        assertTrue(Money(100) < Money(250))
        assertEquals(
            listOf(Money(1), Money(2), Money(3)),
            listOf(Money(3), Money(1), Money(2)).sorted(),
        )
    }

    @Test
    fun `menu DSL collects item names`() {
        val items = menu {
            item("A")
            item("B")
        }
        assertEquals(listOf("A", "B"), items)
    }

    @Test
    fun `safeDivide wraps success and failure`() {
        assertEquals(2, safeDivide(10, 5).getOrNull())
        assertTrue(safeDivide(1, 0).isFailure)
    }

    @Test
    fun `nonBlank delegate ignores blank assignments`() {
        class Holder {
            var name: String by NonBlank("init")
        }
        val h = Holder()
        h.name = "Ada"
        assertEquals("Ada", h.name)
        h.name = "   "
        assertEquals("Ada", h.name)
    }
}
