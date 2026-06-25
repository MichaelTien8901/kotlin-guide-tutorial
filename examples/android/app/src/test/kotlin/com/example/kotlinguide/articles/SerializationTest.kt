package com.example.kotlinguide.articles

import org.junit.Assert.assertEquals
import org.junit.Test

class SerializationTest {
    @Test
    fun `parses a JSON array of articles`() {
        val json = """[{"id":1,"title":"A","body":"x"},{"id":2,"title":"B","body":"y"}]"""
        val articles = ArticleJson.parse(json)
        assertEquals(2, articles.size)
        assertEquals("A", articles[0].title)
    }
}
