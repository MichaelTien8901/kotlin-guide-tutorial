package examples.hello

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExercisesTest {
    @Test
    fun `shout uppercases and appends a bang`() {
        assertEquals("HELLO!", shout("hello"))
    }

    @Test
    fun `greetAll greets one name per line`() {
        assertEquals("Hello, A!\nHello, B!", greetAll("A", "B"))
    }
}
