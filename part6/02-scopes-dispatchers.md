---
layout: default
title: "Scopes, Context & Dispatchers"
parent: "Part 6: Coroutines & Concurrency"
nav_order: 2
---

# Scopes, Context & Dispatchers

## CoroutineScope

Every coroutine runs in a **scope**, which defines its lifetime. When a scope is
cancelled, all coroutines started in it are cancelled too:

```kotlin
suspend fun work() = coroutineScope {   // creates a scope tied to this function
    launch { /* child 1 */ }
    launch { /* child 2 */ }
}   // returns only when both children finish
```

`coroutineScope` waits for its children; if any child fails, the rest are
cancelled.

## CoroutineContext and Job

A coroutine's **context** is a set of elements: a `Job` (its lifecycle handle), a
dispatcher, a name, and an exception handler. A child inherits its parent's
context and overrides parts as needed.

```kotlin
val job = launch(CoroutineName("loader")) {
    // this coroutine's Job is a child of the scope's Job
}
job.cancel()
```

## Dispatchers

A **dispatcher** decides which thread(s) a coroutine runs on:

| Dispatcher | Use for |
|------------|---------|
| `Dispatchers.Default` | CPU-bound work (sorting, parsing) |
| `Dispatchers.IO` | Blocking I/O (network, disk, database) |
| `Dispatchers.Main` | UI updates (Android/desktop; needs a UI library) |
| `Dispatchers.Unconfined` | Advanced/testing — not for general use |

## Switching dispatchers with `withContext`

Move a block of work to a different dispatcher and come back:

```kotlin
suspend fun loadFile(path: String): String =
    withContext(Dispatchers.IO) {        // run the blocking read off the main thread
        File(path).readText()
    }
```

A common pattern on Android: keep coroutines on `Main`, and wrap blocking calls
in `withContext(Dispatchers.IO)`.

{: .tip }
`withContext` does not start a new coroutine — it switches the current one's
context and returns the block's result, making it ideal for "run this part
elsewhere."

## Exercises

1. Use `withContext(Dispatchers.Default)` to run a CPU-heavy computation (e.g.
   summing a large range) and return the result.

2. Explain when you would choose `Dispatchers.IO` over `Dispatchers.Default`.

---

Previous: [Suspend Functions & Builders]({% link part6/01-suspend-builders.md %}) ·
Next: [Structured Concurrency]({% link part6/03-structured-concurrency.md %})
