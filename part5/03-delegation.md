---
layout: default
title: "Delegation"
parent: "Part 5: Functional & Advanced"
nav_order: 3
---

# Delegation

Kotlin has first-class support for **delegation** — forwarding work to another
object — both for interfaces and for properties.

## Class delegation

Implement an interface by delegating to an instance with `by`:

```kotlin
interface Repository {
    fun load(id: Int): String
}

class NetworkRepository : Repository {
    override fun load(id: Int) = "item $id"
}

// LoggingRepository implements Repository by forwarding to `delegate`,
// overriding only what it wants to change.
class LoggingRepository(private val delegate: Repository) : Repository by delegate {
    override fun load(id: Int): String {
        println("loading $id")
        return delegate.load(id)
    }
}
```

`by delegate` generates the forwarding methods for you — no boilerplate.

## Delegated properties

A property can delegate its get/set logic with `by`:

```kotlin
val config: Config by lazy { loadConfig() }     // computed once, on first access

var name: String by Delegates.observable("") { _, old, new ->
    println("name changed: $old -> $new")
}
```

### Storing properties in a map

```kotlin
class User(map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

val u = User(mapOf("name" to "Ada", "age" to 36))
u.name    // "Ada"
```

## Writing a custom delegate

A delegate just needs `getValue` (and `setValue` for `var`):

```kotlin
import kotlin.reflect.KProperty

class UpperCaseDelegate {
    private var stored = ""
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = stored
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        stored = value.uppercase()
    }
}

class Person {
    var name: String by UpperCaseDelegate()
}

val p = Person().apply { name = "ada" }
p.name    // "ADA"
```

## Exercises

1. Add a `lazy` property to a class that prints a message the first time it is
   computed, and confirm the message prints only once.

2. Write a `nonBlank()` delegate (a `var String` property) that rejects blank
   assignments by keeping the previous value.

This solution is in `examples/core/part5/` and is tested by CI.

---

Previous: [Scope Functions]({% link part5/02-scope-functions.md %}) ·
Next: [Operator Overloading]({% link part5/04-operators.md %})
