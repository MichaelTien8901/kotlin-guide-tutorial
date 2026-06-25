---
layout: default
title: "Suspend Functions & Builders"
parent: "Part 6: Coroutines & Concurrency"
nav_order: 1
---

# Suspend Functions & Builders

Coroutines let you write asynchronous code that **reads like sequential code**,
without blocking threads or nesting callbacks.

## Suspend functions

A `suspend` function can pause and resume without blocking its thread:

```kotlin
import kotlinx.coroutines.delay

suspend fun fetchUser(): String {
    delay(1000)            // suspends for 1s — does not block the thread
    return "Ada"
}
```

A `suspend` function can only be called from another `suspend` function or from a
coroutine. `delay` is itself a suspend function.

## Coroutine builders

Builders start coroutines from regular code.

### `runBlocking`

Bridges blocking and suspending worlds — used in `main` and tests:

```kotlin
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val user = fetchUser()
    println(user)
}
```

### `launch` — fire and forget

Starts a coroutine that does not return a result; returns a `Job` you can cancel
or join:

```kotlin
val job = launch {
    delay(500)
    println("done")
}
job.join()                 // wait for it to finish
```

### `async` / `await` — concurrent results

`async` returns a `Deferred<T>`; call `await()` to get the value. Two `async`
blocks run concurrently:

```kotlin
suspend fun loadDashboard() = coroutineScope {
    val user = async { fetchUser() }       // both start...
    val stats = async { fetchStats() }     // ...concurrently
    "${user.await()} has ${stats.await()} points"
}
```

If `fetchUser` and `fetchStats` each take 1s, running them with `async` finishes
in ~1s, not 2s.

## Suspending vs blocking

`Thread.sleep(1000)` **blocks** the thread — nothing else can run on it.
`delay(1000)` **suspends** the coroutine — the thread is free to do other work
while it waits. That is what makes coroutines cheap: you can have hundreds of
thousands of them on a few threads.

## Exercises

1. Write a `suspend fun double(x: Int): Int` that `delay`s 100 ms and returns
   `x * 2`, then call it from `runBlocking`.

2. Use `async` to compute `double(2)` and `double(3)` concurrently and sum the
   results.

This solution is in `examples/core/part6/` and is tested by CI.

---

Next: [Scopes, Context & Dispatchers]({% link part6/02-scopes-dispatchers.md %})
