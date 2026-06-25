package com.example.kotlinguide.articles

/**
 * The data layer's public interface. The ViewModel depends on this, not on a
 * concrete Room/network implementation — that is the repository pattern.
 */
interface ArticleRepository {
    suspend fun articles(): List<Article>
}

val sampleArticles = listOf(
    Article(1, "Welcome", "Hello from Kotlin & Compose"),
    Article(2, "Architecture", "State down, events up"),
)

/** In-memory implementation so the example builds in CI without a backend. */
class InMemoryArticleRepository(
    private val data: List<Article> = sampleArticles,
) : ArticleRepository {
    override suspend fun articles(): List<Article> = data
}
