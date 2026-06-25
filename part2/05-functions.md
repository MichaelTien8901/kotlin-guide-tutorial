---
layout: default
title: "Functions"
parent: "Part 2: Language Basics"
nav_order: 5
---

# Functions

## Declaring a function

```kotlin
fun add(a: Int, b: Int): Int {
    return a + b
}
```

- `fun` introduces a function.
- Parameters are `name: Type`.
- The return type follows the parameter list.

## Single-expression functions

When the body is one expression, drop the braces and `return`:

```kotlin
fun add(a: Int, b: Int): Int = a + b
fun square(x: Int) = x * x          // return type inferred
```

## `Unit` (no return value)

A function that returns nothing has the type `Unit` (similar to `void`). You can
omit it:

```kotlin
fun log(message: String): Unit = println(message)
fun log2(message: String) = println(message)   // Unit inferred
```

## Default and named arguments

Parameters can have defaults, and callers can pass arguments by name:

```kotlin
fun greet(name: String, greeting: String = "Hello", excited: Boolean = false): String {
    val end = if (excited) "!" else "."
    return "$greeting, $name$end"
}

greet("Kotlin")                          // "Hello, Kotlin."
greet("Kotlin", excited = true)          // "Hello, Kotlin!"  (named arg skips greeting)
greet(name = "Ada", greeting = "Hi")     // "Hi, Ada."
```

Named arguments remove the need for many overloads and make call sites readable.

## Variable number of arguments (`vararg`)

```kotlin
fun sum(vararg numbers: Int): Int = numbers.sum()

sum(1, 2, 3)             // 6
val nums = intArrayOf(1, 2, 3)
sum(*nums)               // spread an array with *
```

## Local functions

Functions can be nested to encapsulate helpers:

```kotlin
fun process(data: List<Int>): Int {
    fun isValid(x: Int) = x in 0..100
    return data.filter(::isValid).sum()
}
```

## Exercises

1. Write `greet` with a default `greeting` of `"Hello"` and call it both with and
   without overriding the greeting.

2. Write `factorial(n: Int): Long` returning `n!` (with `factorial(0) == 1`).

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun factorial(n: Int): Long {
       require(n >= 0) { "n must be non-negative" }
       var result = 1L
       for (i in 2..n) result *= i
       return result
   }
   {% endhighlight %}

   </details>

---

Previous: [Control Flow]({% link part2/04-control-flow.md %}) ·
Next: [Part 3: OOP & Functions]({% link part3/index.md %})
