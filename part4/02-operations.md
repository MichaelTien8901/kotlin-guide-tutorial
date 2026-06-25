---
layout: default
title: "Collection Operations"
parent: "Part 4: Collections & Generics"
nav_order: 2
---

# Collection Operations

The standard library has a rich set of functional operations that read like a
pipeline. They return **new** collections and never mutate the source.

## Transforming

```kotlin
val nums = listOf(1, 2, 3, 4)
nums.map { it * it }            // [1, 4, 9, 16]
nums.mapIndexed { i, n -> i to n }
listOf("a,b", "c").flatMap { it.split(",") }   // [a, b, c]
```

## Filtering

```kotlin
nums.filter { it % 2 == 0 }    // [2, 4]
nums.filterNot { it % 2 == 0 } // [1, 3]
nums.partition { it > 2 }      // ([3, 4], [1, 2])
listOf(1, null, 2).filterNotNull()  // [1, 2]
```

## Aggregating

```kotlin
nums.sum()                     // 10
nums.sumOf { it * 2 }          // 20
nums.count { it > 2 }          // 2
nums.fold(100) { acc, n -> acc + n }   // 110
nums.reduce { acc, n -> acc * n }      // 24
nums.maxOrNull()               // 4
```

## Grouping and associating

```kotlin
val words = listOf("apple", "banana", "avocado", "cherry")
words.groupBy { it.first() }
// {a=[apple, avocado], b=[banana], c=[cherry]}

words.associateWith { it.length }
// {apple=5, banana=6, ...}

words.associateBy { it.first() }  // keep one per key
```

## Ordering

```kotlin
nums.sorted()                  // ascending
nums.sortedDescending()
words.sortedBy { it.length }
words.sortedByDescending { it.length }
```

## Chaining

Operations compose into readable pipelines:

```kotlin
val result = (1..10)
    .filter { it % 2 == 0 }
    .map { it * it }
    .sum()                     // 4 + 16 + 36 + 64 + 100 = 220
```

{: .tip }
Each step here allocates an intermediate list. For large data or long chains,
use a `Sequence` (next chapter) to evaluate lazily.

## Exercises

1. Given a list of words, produce a list of their lengths, keeping only those
   longer than 3.

2. Write `topN(numbers: List<Int>, n: Int): List<Int>` returning the `n` largest
   numbers, in descending order.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun topN(numbers: List<Int>, n: Int): List<Int> =
       numbers.sortedDescending().take(n)
   {% endhighlight %}

   </details>

This solution is in `examples/core/part4/` and is tested by CI.

---

Previous: [Collections]({% link part4/01-collections.md %}) ·
Next: [Sequences]({% link part4/03-sequences.md %})
