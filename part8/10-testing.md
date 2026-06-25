---
layout: default
title: "Testing"
parent: "Part 8: Advanced Android App Development"
nav_order: 10
---

# Testing

A layered architecture is also a **testable** architecture. Most logic can be
covered by fast JVM unit tests; only UI behavior needs the Compose test rule or
an emulator.

## The testing pyramid

| Test type | Runs on | Speed | Tests |
|-----------|---------|-------|-------|
| Unit | JVM | fast | ViewModels, use cases, repositories, pure logic |
| Compose UI | JVM (Robolectric) or device | medium | composable behavior |
| Instrumented / E2E | emulator/device | slow | full flows, Room, navigation |

CI runs the unit (and Robolectric-backed Compose) tests; instrumented tests are
run locally.

## Unit-testing a ViewModel

Inject a **fake** repository and a **test dispatcher**:

```kotlin
class FakeArticleRepository(private val data: List<Article>) : ArticleRepository {
    override suspend fun articles(): List<Article> = data
}

@OptIn(ExperimentalCoroutinesApi::class)
class ArticlesViewModelTest {
    @Test
    fun `load emits Success`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        val vm = ArticlesViewModel(FakeArticleRepository(listOf(Article(1, "t", "b"))))

        vm.load()
        advanceUntilIdle()

        val state = vm.uiState.value
        assertTrue(state is ArticlesUiState.Success)
        Dispatchers.resetMain()
    }
}
```

The example app's `ArticlesViewModelTest` follows this exact pattern and runs in
CI.

## Compose UI tests

```kotlin
class CounterScreenTest {
    @get:Rule val rule = createComposeRule()

    @Test
    fun increments() {
        rule.setContent { CounterScreen() }
        rule.onNodeWithText("Increment").performClick()
        rule.onNodeWithText("Count: 1").assertExists()
    }
}
```

Run these on the JVM with **Robolectric** (`testImplementation`) or on a device
as instrumented tests.

## Testing flows

Use `runTest` and collect, or libraries like Turbine:

```kotlin
@Test
fun emits() = runTest {
    val values = repository.articlesFlow().first()
    assertEquals(2, values.size)
}
```

## Exercises

1. Write a `FakeArticleRepository` that throws, and assert the ViewModel emits
   `Error`.

2. Add a Compose UI test asserting the `Reset` button returns the count to 0.

---

Previous: [Modularization & Build Variants]({% link part8/09-modularization.md %}) ·
Next: [Performance & Best Practices]({% link part8/11-performance.md %})
