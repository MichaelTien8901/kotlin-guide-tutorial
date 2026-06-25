package com.example.kotlinguide.articles

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/** Domain model used by the UI layer. */
data class Article(val id: Int, val title: String, val body: String)

/** Transport model parsed from JSON (kotlinx.serialization). */
@Serializable
data class ArticleDto(val id: Int, val title: String, val body: String)

/** Parses a JSON array of articles and maps DTOs to domain models. */
object ArticleJson {
    private val json = Json { ignoreUnknownKeys = true }

    fun parse(text: String): List<Article> =
        json.decodeFromString<List<ArticleDto>>(text)
            .map { Article(it.id, it.title, it.body) }
}
