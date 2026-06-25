---
layout: default
title: "Sequences"
parent: "Part 4: Collections & Generics"
nav_order: 3
---

# Sequences

A `Sequence` evaluates **lazily**: elements flow through the pipeline one at a
time, and operations run only as far as needed. This avoids building
intermediate collections and enables early termination.

## Eager vs lazy

```kotlin
// Eager: each step builds a full new list
val eager = listOf(1, 2, 3, 4, 5)
    .map { it * it }       // [1, 4, 9, 16, 25]
    .filter { it > 5 }     // [9, 16, 25]
    .first()               // 9  — but all elements were processed twice

// Lazy: elements pulled on demand, stops at the first match
val lazy = listOf(1, 2, 3, 4, 5)
    .asSequence()
    .map { it * it }
    .filter { it > 5 }
    .first()               // 9  — only processes 1, 2, 3
```

With the sequence, `map` and `filter` run only for the elements needed to produce
the first result.

## Creating sequences

```kotlin
sequenceOf(1, 2, 3)
listOf(1, 2, 3).asSequence()

generateSequence(1) { it * 2 }   // infinite: 1, 2, 4, 8, ...
    .take(5)
    .toList()                    // [1, 2, 4, 8, 16]
```

`generateSequence` plus `take` is a common way to work with infinite streams
safely.

## Terminal vs intermediate operations

- **Intermediate** (`map`, `filter`, `take`) are lazy and return a `Sequence`.
- **Terminal** (`toList`, `sum`, `first`, `count`) trigger evaluation.

A sequence does nothing until a terminal operation is called.

## When to use which

| Use a `List` | Use a `Sequence` |
|--------------|------------------|
| Small collections | Large or huge collections |
| Few operations | Long operation chains |
| You need the intermediate results | You only need the final result |
| — | Infinite or generated data |

{: .note }
For small lists, eager operations are often faster (no per-element iterator
overhead). Reach for sequences when data is large or chains are long.

## Exercises

1. Use `generateSequence` to produce the first 10 powers of two.

2. Write `firstSquareOver(limit: Int): Int` that returns the first perfect square
   (1, 4, 9, ...) strictly greater than `limit`, using a sequence.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun firstSquareOver(limit: Int): Int =
       generateSequence(1) { it + 1 }
           .map { it * it }
           .first { it > limit }
   {% endhighlight %}

   </details>

This solution is in `examples/core/part4/` and is tested by CI.

---

Previous: [Collection Operations]({% link part4/02-operations.md %}) ·
Next: [Generics]({% link part4/04-generics.md %})
