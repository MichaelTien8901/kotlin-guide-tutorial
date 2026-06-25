---
layout: default
title: "Networking"
parent: "Part 8: Advanced Android App Development"
nav_order: 6
---

# Networking

Most apps fetch data from a REST API. The common stack is **Retrofit** (HTTP) +
**kotlinx.serialization** (JSON). Ktor's client is a multiplatform alternative.

## Serialization

Annotate models with `@Serializable`. The kotlinx.serialization compiler plugin
generates the parsing code:

```kotlin
// build.gradle.kts
plugins { id("org.jetbrains.kotlin.plugin.serialization") }
dependencies { implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.x") }
```

```kotlin
@Serializable
data class ArticleDto(val id: Int, val title: String, val body: String)

val json = Json { ignoreUnknownKeys = true }
val articles: List<ArticleDto> = json.decodeFromString(responseBody)
```

The example app uses kotlinx.serialization to parse a JSON payload — that part is
CI-tested.

## Retrofit

Declare the API as an interface with `suspend` functions:

```kotlin
interface ArticleApi {
    @GET("articles")
    suspend fun articles(): List<ArticleDto>

    @GET("articles/{id}")
    suspend fun article(@Path("id") id: Int): ArticleDto
}
```

Build it with the kotlinx.serialization converter:

```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://example.com/api/")
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

val api: ArticleApi = retrofit.create(ArticleApi::class.java)
```

## Error handling and DTO → domain mapping

Wrap calls and map transport models (`ArticleDto`) to domain models (`Article`)
in the repository:

```kotlin
class RemoteArticleRepository(private val api: ArticleApi) : ArticleRepository {
    override suspend fun articles(): List<Article> =
        api.articles().map { Article(it.id, it.title, it.body) }
}

// at the call site
val result = runCatching { repository.articles() }
result.onFailure { /* show error state */ }
```

Run network calls off the main thread — `Dispatchers.IO` (or let Retrofit's
suspend functions handle it, which they do).

## Ktor client (alternative)

```kotlin
val client = HttpClient(CIO) {
    install(ContentNegotiation) { json() }
}
val articles: List<ArticleDto> = client.get("https://example.com/api/articles").body()
```

## Exercises

1. Define a `@Serializable` `UserDto` and parse a JSON array of users with
   `Json.decodeFromString`.

2. Write a `Retrofit` `ApiService` interface with a `suspend` GET endpoint.

---

Previous: [Local Persistence with Room]({% link part8/05-room.md %}) ·
Next: [Dependency Injection with Hilt]({% link part8/07-hilt-di.md %})
