---
layout: default
title: "Generics"
parent: "Part 4: Collections & Generics"
nav_order: 4
---

# Generics

Generics let you write code that works for many types while keeping full type
safety — no casting, no `Any`.

## Generic functions

A type parameter in angle brackets stands in for a real type chosen at the call
site:

```kotlin
fun <T> firstOrNull(items: List<T>): T? =
    if (items.isEmpty()) null else items[0]

firstOrNull(listOf("a", "b"))   // T = String
firstOrNull(listOf(1, 2))       // T = Int
```

## Generic classes

```kotlin
class Box<T>(val value: T) {
    fun <R> map(transform: (T) -> R): Box<R> = Box(transform(value))
}

val boxed = Box(21).map { it * 2 }   // Box<Int> holding 42
```

## Constraints (upper bounds)

Restrict a type parameter so you can call certain members:

```kotlin
fun <T : Comparable<T>> largest(items: List<T>): T? =
    items.maxOrNull()                 // needs Comparable to compare

largest(listOf(3, 1, 2))             // 3
largest(listOf("b", "a"))            // "b"
```

For multiple bounds, use a `where` clause:

```kotlin
fun <T> sortedCopy(items: List<T>): List<T>
    where T : Comparable<T>, T : Any = items.sorted()
```

## Nullability and generics

A bare type parameter `T` may be nullable. Add the `: Any` bound to forbid null:

```kotlin
fun <T : Any> requireAll(items: List<T?>): List<T> = items.filterNotNull()
```

## Exercises

1. Write a generic function `pairUp<T>(a: T, b: T): List<T>` returning a list of
   the two arguments.

2. Write `largest<T : Comparable<T>>(items: List<T>): T?` returning the maximum,
   or `null` for an empty list.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun <T : Comparable<T>> largest(items: List<T>): T? = items.maxOrNull()
   {% endhighlight %}

   </details>

This solution is in `examples/core/part4/` and is tested by CI.

---

Previous: [Sequences]({% link part4/03-sequences.md %}) ·
Next: [Variance & Reified Types]({% link part4/05-variance.md %})
