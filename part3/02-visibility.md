---
layout: default
title: "Visibility & Packages"
parent: "Part 3: OOP & Functions"
nav_order: 2
---

# Visibility & Packages

## Packages and imports

Each file can declare a package; imports bring in names from elsewhere:

```kotlin
package com.example.banking

import kotlin.math.max
```

The package should usually mirror the directory structure, though Kotlin does
not strictly require it.

## Visibility modifiers

| Modifier | Visible to |
|----------|-----------|
| `public` (default) | Everyone |
| `internal` | The same Gradle module |
| `protected` | The declaring class and subclasses |
| `private` | The declaring class (or file, for top-level declarations) |

```kotlin
class Vault {
    private val secret = "🔑"          // only inside Vault
    internal val auditId = 42          // anywhere in this module
    protected open val policy = "std"   // subclasses can see it
    fun reveal() = secret              // public by default
}
```

For **top-level** declarations, `private` means "visible only within this
file":

```kotlin
private fun helper() { /* file-private */ }
```

{: .note }
There is no `package-private` like Java. The closest equivalent is `internal`,
which is scoped to the whole module rather than a single package.

## Why restrict visibility?

Keeping fields `private` and exposing behavior through public methods is
**encapsulation** — it lets you change internals without breaking callers and
keeps invariants (like a non-negative balance) enforced in one place.

## Exercises

1. Take the `BankAccount` from the previous chapter and make its `balance`
   setter `private` so callers must use `deposit`/`withdraw`. (It already is —
   confirm why `account.balance = -100` does not compile.)

2. Add an `internal` function `auditLine(): String` that returns a one-line
   summary, usable from elsewhere in the module but not from outside it.

---

Previous: [Classes & Properties]({% link part3/01-classes.md %}) ·
Next: [Inheritance & Interfaces]({% link part3/03-inheritance-interfaces.md %})
