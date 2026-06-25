---
layout: default
title: "Variables & Types"
parent: "Part 2: Language Basics"
nav_order: 1
---

# Variables & Types

## `val` vs `var`

Kotlin has two ways to declare a variable:

```kotlin
val name = "Kotlin"   // read-only (cannot be reassigned)
var count = 0          // mutable (can be reassigned)

count = 1              // OK
// name = "Java"       // compile error: val cannot be reassigned
```

Prefer `val`. Using read-only references by default makes code easier to reason
about. Reach for `var` only when a value genuinely needs to change.

{: .note }
`val` makes the **reference** read-only, not the object. A `val` list can still
have items added if the list itself is mutable. We cover that in
[Part 4]({% link part4/index.md %}).

## Basic types

| Type | Example | Notes |
|------|---------|-------|
| `Int` | `42` | 32-bit integer |
| `Long` | `42L` | 64-bit integer |
| `Double` | `3.14` | 64-bit floating point (default for decimals) |
| `Float` | `3.14f` | 32-bit floating point |
| `Boolean` | `true` | `true` / `false` |
| `Char` | `'K'` | a single character |
| `String` | `"hi"` | text |

Everything is an object in Kotlin — there are no Java-style primitives in the
language (the compiler optimizes them to primitives where possible).

## Type inference

Kotlin infers the type from the initializer, so you rarely write it explicitly:

```kotlin
val message = "Hello"     // inferred String
val pi = 3.14159          // inferred Double
val isReady = true        // inferred Boolean
```

You can be explicit when you want to — for example to widen or to document:

```kotlin
val total: Long = 0
val ratio: Double = 1
```

## Number literals

```kotlin
val million = 1_000_000     // underscores for readability
val hex = 0xFF              // hexadecimal
val binary = 0b1010         // binary
val big = 10_000_000_000L   // Long
```

## No implicit conversions

Kotlin does **not** silently widen numbers. Convert explicitly:

```kotlin
val i = 10
val l: Long = i.toLong()   // explicit; `val l: Long = i` is an error
val d = i.toDouble()
```

## Exercises

1. Declare a `val` for your name and a `var` for your age, then print a sentence
   using both.

2. Write a function `circleArea(radius: Double): Double` that returns the area of
   a circle (π × r²). Use `Math.PI`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun circleArea(radius: Double): Double = Math.PI * radius * radius
   {% endhighlight %}

   </details>

This solution is in `examples/core/part2/` and is tested by CI.

---

Next: [Strings]({% link part2/02-strings.md %})
