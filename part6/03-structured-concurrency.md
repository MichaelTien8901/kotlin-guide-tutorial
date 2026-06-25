---
layout: default
title: "Structured Concurrency"
parent: "Part 6: Coroutines & Concurrency"
nav_order: 3
---

# Structured Concurrency

**Structured concurrency** means coroutines form a parent-child tree: a parent
does not complete until its children do, and cancelling a parent cancels its
children. This prevents leaked coroutines and makes error handling predictable.

## The scope tree

```kotlin
suspend fun loadAll() = coroutineScope {     // parent scope
    val a = async { loadA() }                // child
    val b = async { loadB() }                // child
    a.await() + b.await()
}   // does not return until both children complete
```

If `loadA()` throws, `loadB()` is cancelled and `loadAll()` rethrows — no
dangling work.

## Cancellation is cooperative

Cancellation does not force-stop a coroutine; the coroutine must **cooperate** by
suspending (most suspend functions check for cancellation) or checking
`isActive`:

```kotlin
val job = launch {
    while (isActive) {           // stops promptly when cancelled
        computeChunk()
    }
}
delay(100)
job.cancelAndJoin()
```

Always let `CancellationException` propagate — do not swallow it in a
`catch (e: Exception)`.

## Timeouts

```kotlin
val result = withTimeoutOrNull(1000) {       // null if it takes too long
    slowOperation()
}

withTimeout(1000) { slowOperation() }        // throws TimeoutCancellationException
```

## Exception propagation: `SupervisorJob`

By default, a failing child cancels its siblings. A **supervisor** scope isolates
failures so one child's failure does not cancel the others:

```kotlin
supervisorScope {
    launch { mightFail() }       // if this fails...
    launch { keepsRunning() }    // ...this one continues
}
```

Handle uncaught failures in `launch` with a `CoroutineExceptionHandler`:

```kotlin
val handler = CoroutineExceptionHandler { _, e -> println("caught $e") }
scope.launch(handler) { error("boom") }
```

## Exercises

1. Use `withTimeoutOrNull` to give a slow `suspend` function 50 ms and return a
   fallback value when it does not finish.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   suspend fun loadOrFallback(): String =
       withTimeoutOrNull(50) { slowLoad() } ?: "fallback"
   {% endhighlight %}

   </details>

2. Show that a failure in one `async` child cancels a sibling within
   `coroutineScope`.

This solution is in `examples/core/part6/` and is tested by CI.

---

Previous: [Scopes, Context & Dispatchers]({% link part6/02-scopes-dispatchers.md %}) ·
Next: [Asynchronous Streams with Flow]({% link part6/04-flow.md %})
