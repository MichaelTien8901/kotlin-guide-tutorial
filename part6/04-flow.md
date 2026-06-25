---
layout: default
title: "Asynchronous Streams with Flow"
parent: "Part 6: Coroutines & Concurrency"
nav_order: 4
---

# Asynchronous Streams with Flow

A `Flow` is a **cold** asynchronous stream of values — like a suspending
`Sequence`. It produces values over time and only runs when collected.

## Building a flow

```kotlin
import kotlinx.coroutines.flow.*

fun numbers(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)            // emit a value
    }
}
```

Other builders: `flowOf(1, 2, 3)` and `listOf(1, 2, 3).asFlow()`.

## Collecting

A flow does nothing until collected (in a coroutine):

```kotlin
numbers().collect { value ->
    println(value)         // 1, 2, 3 — one every 100 ms
}
```

## Operators

Flows have the same functional operators as collections, plus async-aware ones:

```kotlin
numbers()
    .map { it * it }
    .filter { it % 2 == 1 }
    .toList()              // terminal: [1, 9]
```

Intermediate operators (`map`, `filter`, `take`) are lazy; terminal operators
(`collect`, `toList`, `first`, `reduce`) trigger collection.

## Cold vs hot: StateFlow and SharedFlow

A cold flow restarts for each collector. **Hot** flows stay active and share
emissions:

- `StateFlow` — always holds the latest value; great for UI state.
- `SharedFlow` — broadcasts events to multiple collectors.

```kotlin
val state = MutableStateFlow(0)
state.value = 1            // update
val current = state.value  // read the latest

state.update { it + 1 }    // atomic update
```

`StateFlow` is the backbone of modern Android UI state — you will use it heavily
in [Part 8]({% link part8/index.md %}).

## Flow context

A flow's emission runs in the collector's context by default. Move upstream work
with `flowOn`:

```kotlin
dataFlow()
    .map { heavyTransform(it) }
    .flowOn(Dispatchers.Default)   // upstream runs on Default
    .collect { render(it) }        // collection stays on the caller's context
```

## Exercises

1. Create a `Flow<Int>` of 1..5, square each value, and collect the results into a
   list.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   suspend fun squaredList(): List<Int> =
       (1..5).asFlow().map { it * it }.toList()
   {% endhighlight %}

   </details>

2. Use a `MutableStateFlow<Int>` as a counter and update it with `update`.

This solution is in `examples/core/part6/` and is tested by CI.

---

Previous: [Structured Concurrency]({% link part6/03-structured-concurrency.md %}) ·
Next: [Channels]({% link part6/05-channels.md %})
