package com.example.kotlinguide

import org.junit.Assert.assertEquals
import org.junit.Test

/** Local unit test (runs on the JVM, no emulator needed). */
class ExampleUnitTest {
    @Test
    fun greetingText_includesName() {
        assertEquals("Hello, Kotlin!", greetingText("Kotlin"))
    }
}
