---
layout: default
title: "Classes & Properties"
parent: "Part 3: OOP & Functions"
nav_order: 1
---

# Classes & Properties

## Declaring a class

```kotlin
class Person(val name: String, var age: Int)

val p = Person("Ada", 36)   // no `new` keyword
println(p.name)             // Ada
p.age = 37                  // var property can change
```

The parameters in parentheses form the **primary constructor**. Prefixing them
with `val`/`var` declares them as properties in one line.

## Initialization

Run setup code in an `init` block:

```kotlin
class Account(val owner: String) {
    val createdAt: Long

    init {
        createdAt = System.currentTimeMillis()
        require(owner.isNotBlank()) { "owner is required" }
    }
}
```

## Properties with custom accessors

A property can compute its value or validate on assignment:

```kotlin
class Rectangle(val width: Int, val height: Int) {
    val area: Int
        get() = width * height          // computed, no backing field

    var label: String = ""
        set(value) {                    // custom setter
            field = value.trim()        // `field` is the backing field
        }
}
```

## Secondary constructors

```kotlin
class Point(val x: Int, val y: Int) {
    constructor(value: Int) : this(value, value)   // delegates to primary
}
```

## `lateinit` and `lazy`

Defer initialization when you cannot set a value up front:

```kotlin
class Service {
    lateinit var client: HttpClient        // assigned later; non-null var
    val config by lazy { loadConfig() }     // computed once, on first access
}
```

Use `lateinit` for `var` properties initialized after construction; use `lazy`
for `val` properties you want computed on first use.

## Exercises

1. Create a `Temperature` class with a `celsius` property and a computed
   `fahrenheit` property (`c * 9/5 + 32`).

2. Write a `BankAccount` class with a read-only `balance`, and `deposit(amount)`
   / `withdraw(amount)` methods that reject non-positive or overdrawn amounts.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   class BankAccount(initial: Long = 0) {
       var balance: Long = initial
           private set

       fun deposit(amount: Long) {
           require(amount > 0) { "amount must be positive" }
           balance += amount
       }

       fun withdraw(amount: Long) {
           require(amount > 0) { "amount must be positive" }
           require(amount <= balance) { "insufficient funds" }
           balance -= amount
       }
   }
   {% endhighlight %}

   </details>

This solution is in `examples/core/part3/` and is tested by CI.

---

Next: [Visibility & Packages]({% link part3/02-visibility.md %})
