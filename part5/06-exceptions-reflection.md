---
layout: default
title: "Exceptions & Reflection"
parent: "Part 5: Functional & Advanced"
nav_order: 6
---

# Exceptions & Reflection

## Exceptions

Kotlin exceptions work like Java's, but **all are unchecked** — you are never
forced to declare or catch them.

```kotlin
fun parse(input: String): Int {
    try {
        return input.toInt()
    } catch (e: NumberFormatException) {
        return -1
    } finally {
        println("done")
    }
}
```

`try` is also an **expression**:

```kotlin
val number = try { input.toInt() } catch (e: Exception) { 0 }
```

### Throwing and preconditions

```kotlin
fun withdraw(amount: Int) {
    require(amount > 0) { "amount must be positive" }   // throws IllegalArgumentException
    check(balance >= amount) { "insufficient funds" }   // throws IllegalStateException
}

fun fail(): Nothing = throw IllegalStateException("unreachable")
```

`Nothing` is the type of an expression that never returns (it always throws),
which helps the compiler with control-flow analysis.

### `runCatching`

Wrap a computation in a `Result` instead of try/catch:

```kotlin
val result: Result<Int> = runCatching { input.toInt() }
val value = result.getOrDefault(0)
result
    .onSuccess { println("ok: $it") }
    .onFailure { println("failed: ${it.message}") }
```

## Annotations

Define metadata that tools or the runtime can read:

```kotlin
annotation class Beta

@Beta
fun experimentalFeature() { /* ... */ }
```

Annotations can take parameters and target specific elements (`@Target`),
and control retention (`@Retention`).

## Reflection (a taste)

Reflection inspects code at runtime. It is powerful but slower than direct
calls — use it sparingly:

```kotlin
val cls = "hello"::class            // KClass<String>
cls.simpleName                      // "String"

data class Point(val x: Int, val y: Int)
Point::class.members.map { it.name } // property/function names
```

{: .note }
Libraries like serialization and dependency injection use reflection (or
compile-time code generation) under the hood. You rarely need it directly in
application code.

## Exercises

1. Write `safeDivide(a: Int, b: Int): Result<Int>` using `runCatching`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun safeDivide(a: Int, b: Int): Result<Int> = runCatching { a / b }
   {% endhighlight %}

   </details>

2. Use `require` to validate a function's input and observe the exception type.

This solution is in `examples/core/part5/` and is tested by CI.

---

Previous: [Type-Safe Builders (DSLs)]({% link part5/05-dsls.md %}) ·
Next: [Part 6: Coroutines & Concurrency]({% link part6/index.md %})
