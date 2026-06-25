---
layout: default
title: "Lambdas & Higher-Order Functions"
parent: "Part 5: Functional & Advanced"
nav_order: 1
---

# Lambdas & Higher-Order Functions

Functions are **first-class** in Kotlin: you can store them in variables, pass
them as arguments, and return them.

## Function types

A function type is written `(Params) -> ReturnType`:

```kotlin
val add: (Int, Int) -> Int = { a, b -> a + b }
add(2, 3)               // 5

val greet: (String) -> String = { name -> "Hi, $name" }
```

## Lambdas

A lambda is a function literal in braces. Its last expression is the return
value, and a single parameter is available as `it`:

```kotlin
val double: (Int) -> Int = { it * 2 }
val nums = listOf(1, 2, 3).map { it * 2 }   // [2, 4, 6]
```

## Higher-order functions

A function that takes or returns another function:

```kotlin
fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (item in this) if (predicate(item)) result.add(item)
    return result
}

listOf(1, 2, 3, 4).customFilter { it % 2 == 0 }   // [2, 4]
```

## Trailing lambda syntax

If the last parameter is a function, the lambda can go outside the parentheses:

```kotlin
listOf(1, 2, 3).fold(0) { acc, n -> acc + n }   // lambda after ()
repeat(3) { println("hi") }                      // only a lambda → no ()
```

## Function references

Refer to an existing function with `::`:

```kotlin
fun isEven(n: Int) = n % 2 == 0
listOf(1, 2, 3, 4).filter(::isEven)    // [2, 4]

listOf("a", "bb").map(String::length)  // [1, 2]
```

## Closures

A lambda captures variables from its surrounding scope:

```kotlin
fun counter(): () -> Int {
    var count = 0
    return { ++count }      // captures and mutates `count`
}

val next = counter()
next(); next(); next()      // 1, 2, 3
```

## `inline` functions

Marking a higher-order function `inline` copies its body (and the lambda) into
the call site, avoiding the overhead of allocating a function object:

```kotlin
inline fun measure(block: () -> Unit) {
    val start = System.nanoTime()
    block()
    println("took ${System.nanoTime() - start} ns")
}
```

Most standard-library functions like `map` and `filter` are `inline`.

## Exercises

1. Write a higher-order function `applyTwice(x: Int, f: (Int) -> Int): Int` that
   returns `f(f(x))`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun applyTwice(x: Int, f: (Int) -> Int): Int = f(f(x))
   {% endhighlight %}

   </details>

2. Use a function reference to map a list of strings to their lengths.

This solution is in `examples/core/part5/` and is tested by CI.

---

Next: [Scope Functions]({% link part5/02-scope-functions.md %})
