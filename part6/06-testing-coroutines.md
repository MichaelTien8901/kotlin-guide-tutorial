---
layout: default
title: "Testing Coroutines"
parent: "Part 6: Coroutines & Concurrency"
nav_order: 6
---

# Testing Coroutines

The `kotlinx-coroutines-test` library makes coroutine code fast and
deterministic to test by replacing real time with **virtual time**.

## `runTest`

Wrap a suspend test body in `runTest`. Calls to `delay` are skipped instantly —
a test that "waits" 10 seconds finishes in milliseconds:

```kotlin
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.delay
import kotlin.test.assertEquals

@Test
fun fetchesUser() = runTest {
    val user = fetchUser()       // any delay() inside is auto-advanced
    assertEquals("Ada", user)
}
```

Add the dependency:

```kotlin
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
```

## Virtual time control

Inside `runTest` you have a `TestScope` with a scheduler you can advance
manually:

```kotlin
@Test
fun advancesTime() = runTest {
    var done = false
    launch {
        delay(1000)
        done = true
    }
    assertEquals(false, done)
    advanceTimeBy(1001)          // move virtual time forward
    advanceUntilIdle()           // run everything pending
    assertEquals(true, done)
}
```

## Injecting dispatchers

Production code should not hard-code dispatchers — inject them so tests can swap
in a test dispatcher:

```kotlin
class UserRepository(private val io: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun load(): String = withContext(io) { /* ... */ "Ada" }
}

@Test
fun loads() = runTest {
    val repo = UserRepository(StandardTestDispatcher(testScheduler))
    assertEquals("Ada", repo.load())
}
```

This dependency-injection pattern is what makes ViewModels testable in
[Part 8]({% link part8/index.md %}).

## Testing flows

Collect a flow into a list inside `runTest`:

```kotlin
@Test
fun emitsThreeValues() = runTest {
    val values = numbers().toList()
    assertEquals(listOf(1, 2, 3), values)
}
```

## Exercises

1. Write a `runTest` that calls a `suspend` function containing `delay(5000)` and
   verify it completes essentially instantly.

2. Test that a `Flow<Int>` of `1..3` collects to `[1, 2, 3]`.

This solution is in `examples/core/part6/` and is tested by CI.

---

Previous: [Channels]({% link part6/05-channels.md %}) ·
Next: [Part 7: Android Foundations]({% link part7/index.md %})
