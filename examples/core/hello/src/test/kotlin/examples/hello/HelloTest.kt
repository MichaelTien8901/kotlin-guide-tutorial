package examples.hello

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HelloTest {
    @Test
    fun `greeting includes the name`() {
        assertEquals("Hello, Kotlin!", greeting("Kotlin"))
    }
}
