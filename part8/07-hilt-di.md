---
layout: default
title: "Dependency Injection with Hilt"
parent: "Part 8: Advanced Android App Development"
nav_order: 7
---

# Dependency Injection with Hilt

**Dependency injection** means objects receive their dependencies instead of
creating them. It makes code testable (swap a fake repository) and decoupled.
You have already used the simplest form — **constructor injection** — throughout
this part:

```kotlin
class ArticlesViewModel(private val repository: ArticleRepository) : ViewModel()
```

**Hilt** automates wiring this graph across the app.

{: .note }
Hilt uses annotation processing and an `Application` subclass. It is shown here as
idiomatic code; the CI example app uses manual constructor injection to stay
buildable without the extra toolchain.

## Setup

```kotlin
// build.gradle.kts (root)
plugins { id("com.google.dagger.hilt.android") version "2.52" apply false }

// app/build.gradle.kts
plugins {
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}
dependencies {
    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-compiler:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
}
```

## Application and entry points

```kotlin
@HiltAndroidApp
class MyApp : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() { /* ... */ }
```

(Register `MyApp` via `android:name=".MyApp"` in the manifest.)

## Providing dependencies

Bind an interface to an implementation, or `@Provides` a built object:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindArticleRepository(impl: RemoteArticleRepository): ArticleRepository
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApi(): ArticleApi = Retrofit.Builder()/* ... */.create(ArticleApi::class.java)
}
```

## Injecting into a ViewModel

```kotlin
@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val repository: ArticleRepository,
) : ViewModel() { /* ... */ }
```

Obtain it in Compose with `hiltViewModel()`:

```kotlin
@Composable
fun ArticlesRoute(viewModel: ArticlesViewModel = hiltViewModel()) { /* ... */ }
```

## Why bother?

Hilt removes manual factory boilerplate and gives you scoped, lifecycle-aware
singletons. For tests, you can replace modules with fakes — or skip Hilt entirely
and construct the ViewModel with a fake repository, as the example's tests do.

## Exercises

1. Convert a manually-constructed repository to a Hilt `@Binds` module (on paper
   or in a scratch project).

2. Explain why depending on the `ArticleRepository` **interface** (not the
   implementation) makes the ViewModel easy to test.

---

Previous: [Networking]({% link part8/06-networking.md %}) ·
Next: [Background Work with WorkManager]({% link part8/08-workmanager.md %})
