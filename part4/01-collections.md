---
layout: default
title: "Collections"
parent: "Part 4: Collections & Generics"
nav_order: 1
---

# Collections

Kotlin's standard library provides three core collection types — `List`, `Set`,
and `Map` — each in **read-only** and **mutable** flavors.

## Read-only vs mutable

```kotlin
val readOnly = listOf(1, 2, 3)            // List<Int> — no add/remove
val mutable = mutableListOf(1, 2, 3)      // MutableList<Int>
mutable.add(4)

// readOnly.add(4)  // compile error: List has no `add`
```

The read-only interfaces (`List`, `Set`, `Map`) do not expose mutators. Prefer
them — expose `List` from a function unless callers genuinely need to mutate.

{: .note }
Read-only is not the same as immutable: a `List` may be a view over a
`MutableList` that something else can change. It simply means *you* cannot mutate
it through this reference.

## Lists

```kotlin
val nums = listOf(10, 20, 30)
nums[0]                 // 10  (indexing)
nums.first()            // 10
nums.last()             // 30
nums.size               // 3
20 in nums              // true
```

## Sets (unique elements)

```kotlin
val s = setOf(1, 2, 2, 3)     // {1, 2, 3}
s.contains(2)                 // true
val m = mutableSetOf<String>()
m.add("a"); m.add("a")        // second add is a no-op
```

## Maps (key → value)

```kotlin
val ages = mapOf("Ada" to 36, "Alan" to 41)
ages["Ada"]                   // 36
ages.getOrDefault("Bob", 0)   // 0
ages.keys                     // [Ada, Alan]
ages.values                   // [36, 41]

val counts = mutableMapOf<String, Int>()
counts["x"] = (counts["x"] ?: 0) + 1
```

`to` is an infix function that builds a `Pair`.

## Arrays

Arrays are fixed-size and mostly used for interop or performance:

```kotlin
val arr = intArrayOf(1, 2, 3)
val strs = arrayOf("a", "b")
arr[0] = 9
```

Prefer `List` in everyday code; reach for arrays when an API requires them.

## Exercises

1. Build a `Map<String, Int>` of three people to their ages and print each on its
   own line.

2. Write `wordFrequencies(text: String): Map<String, Int>` that counts how many
   times each (lowercased) word appears.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun wordFrequencies(text: String): Map<String, Int> =
       text.lowercase()
           .split(Regex("\\W+"))
           .filter { it.isNotEmpty() }
           .groupingBy { it }
           .eachCount()
   {% endhighlight %}

   </details>

This solution is in `examples/core/part4/` and is tested by CI.

---

Next: [Collection Operations]({% link part4/02-operations.md %})
