---
layout: default
title: "Objects & Companions"
parent: "Part 3: OOP & Functions"
nav_order: 5
---

# Objects & Companions

## `object` — singletons

The `object` keyword declares a class **and** its single instance at once:

```kotlin
object AppConfig {
    val version = "1.0"
    fun describe() = "App v$version"
}

AppConfig.version          // access directly — no constructor
```

Use it for stateless helpers, registries, or anything there should be exactly one
of.

## Companion objects

A `companion object` holds members tied to the class itself rather than to
instances — the closest thing to Java's `static`:

```kotlin
class User private constructor(val name: String) {
    companion object {
        fun create(name: String): User = User(name.trim())   // factory
        const val MAX_NAME_LENGTH = 50
    }
}

val u = User.create("  Ada  ")    // call on the class
User.MAX_NAME_LENGTH              // 50
```

A common pattern: make the constructor `private` and expose a factory method on
the companion that validates input.

## Anonymous objects

Create a one-off object, often to implement an interface on the spot:

```kotlin
interface Listener { fun onEvent(name: String) }

val logger = object : Listener {
    override fun onEvent(name: String) = println("event: $name")
}
```

## Object expressions vs declarations

- `object Name { ... }` — a named singleton (declaration).
- `object : Type { ... }` — an anonymous instance (expression), evaluated where
  it appears.

## Exercises

1. Create an `object Counter` with a private `var count` and `increment()` /
   `current()` functions.

2. Give a `Color` class a `companion object` with a factory `fromHex(hex: String)`
   and a constant `BLACK`.

---

Previous: [Data, Enum & Sealed Classes]({% link part3/04-data-enum-sealed.md %}) ·
Next: [Extension Functions]({% link part3/06-extensions.md %})
