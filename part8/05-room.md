---
layout: default
title: "Local Persistence with Room"
parent: "Part 8: Advanced Android App Development"
nav_order: 5
---

# Local Persistence with Room

**Room** is the recommended SQLite library for Android. You define entities,
DAOs, and a database; Room generates the boilerplate at compile time.

{: .note }
Room uses an annotation processor (KSP). It is shown here as focused, idiomatic
code you can drop into a project. The CI example app uses an in-memory repository
to stay buildable without the extra toolchain — see the
[Examples notes]({% link part8/index.md %}).

## Setup

```kotlin
// build.gradle.kts
plugins {
    id("com.google.devtools.ksp")
}
dependencies {
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")   // coroutines/Flow support
    ksp("androidx.room:room-compiler:2.6.1")
}
```

## Entity

```kotlin
@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
)
```

## DAO

A DAO declares queries. Returning a `Flow` makes them observable — the UI updates
automatically when the table changes:

```kotlin
@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY id")
    fun observeAll(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity)

    @Query("DELETE FROM articles")
    suspend fun clear()
}
```

## Database

```kotlin
@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}

val db = Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()
```

## Using it in the data layer

The repository maps entities to domain models, hiding Room from the rest of the
app:

```kotlin
class LocalArticleRepository(private val dao: ArticleDao) : ArticleRepository {
    override fun articlesFlow(): Flow<List<Article>> =
        dao.observeAll().map { rows -> rows.map { Article(it.id, it.title, it.body) } }

    override suspend fun articles(): List<Article> = articlesFlow().first()
}
```

## Migrations

When the schema changes, bump `version` and provide a `Migration`, or use
`fallbackToDestructiveMigration()` during early development. Export the schema for
tested migrations with the Room schema directory.

## Exercises

1. Add an `isRead: Boolean` column to `ArticleEntity` and a query for unread
   articles.

2. Add a `@Query` that returns a single article by id as a `Flow<ArticleEntity?>`.

---

Previous: [ViewModel & State Management]({% link part8/04-viewmodel-state.md %}) ·
Next: [Networking]({% link part8/06-networking.md %})
