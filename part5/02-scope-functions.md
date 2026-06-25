---
layout: default
title: "Scope Functions"
parent: "Part 5: Functional & Advanced"
nav_order: 2
---

# Scope Functions

The standard library provides five **scope functions** — `let`, `run`, `with`,
`apply`, and `also` — that execute a block in the context of an object. They
differ in two ways: how the object is referenced (`it` vs `this`), and what the
block returns.

## The cheat sheet

| Function | Object reference | Returns | Typical use |
|----------|------------------|---------|-------------|
| `let` | `it` | block result | null-checks, transform |
| `run` | `this` | block result | configure + compute a result |
| `with` | `this` | block result | group calls on an object |
| `apply` | `this` | the object | configure and return it |
| `also` | `it` | the object | side effects (logging) |

## `let`

Transform a value, or run code only when non-null:

```kotlin
val length = "hello".let { it.length }     // 5

nickname?.let {
    println("Nickname: $it")               // runs only if non-null
}
```

## `run`

Like `let`, but the receiver is `this`. Good for configuring then returning a
result:

```kotlin
val area = rectangle.run { width * height }
```

## `with`

Group several calls on the same object (not an extension):

```kotlin
val summary = with(user) {
    "$name is $age years old"
}
```

## `apply`

Configure an object and return **the object itself** — great for builders:

```kotlin
val file = StringBuilder().apply {
    append("line 1\n")
    append("line 2\n")
}.toString()
```

## `also`

Perform a side effect and return the object unchanged:

```kotlin
val nums = mutableListOf(1, 2)
    .also { println("before: $it") }
    .apply { add(3) }
    .also { println("after: $it") }
```

## Choosing one

- Return a **result**? → `let` (uses `it`) or `run` (uses `this`).
- Return the **object** after configuring it? → `apply` (uses `this`).
- Return the **object** after a side effect? → `also` (uses `it`).

{: .tip }
Do not overuse them — a plain local variable is often clearer than a nested chain
of scope functions.

## Exercises

1. Use `apply` to create a configured `StringBuilder` and return its string.

2. Write `formatUser(name: String?, age: Int): String` that returns
   `"name (age)"`, or `"unknown (age)"` when `name` is null — using `let` and the
   Elvis operator.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun formatUser(name: String?, age: Int): String =
       (name?.let { it } ?: "unknown") + " ($age)"
   {% endhighlight %}

   </details>

This solution is in `examples/core/part5/` and is tested by CI.

---

Previous: [Lambdas & Higher-Order Functions]({% link part5/01-lambdas-hof.md %}) ·
Next: [Delegation]({% link part5/03-delegation.md %})
