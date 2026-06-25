---
layout: default
title: "Operator Overloading"
parent: "Part 5: Functional & Advanced"
nav_order: 4
---

# Operator Overloading

Kotlin lets types define what operators like `+`, `[]`, and `in` mean, by
implementing specially-named `operator` functions.

## Arithmetic operators

```kotlin
data class Vector(val x: Int, val y: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
    operator fun times(scale: Int) = Vector(x * scale, y * scale)
}

Vector(1, 2) + Vector(3, 4)    // Vector(4, 6)
Vector(1, 2) * 3               // Vector(3, 6)
```

| Operator | Function |
|----------|----------|
| `a + b` | `a.plus(b)` |
| `a - b` | `a.minus(b)` |
| `a * b` | `a.times(b)` |
| `a in b` | `b.contains(a)` |
| `a[i]` | `a.get(i)` |
| `a[i] = v` | `a.set(i, v)` |
| `a()` | `a.invoke()` |

## Indexing

```kotlin
class Grid(val width: Int, val height: Int) {
    private val cells = IntArray(width * height)
    operator fun get(x: Int, y: Int) = cells[y * width + x]
    operator fun set(x: Int, y: Int, value: Int) { cells[y * width + x] = value }
}

val grid = Grid(3, 3)
grid[1, 1] = 5
grid[1, 1]              // 5
```

## `invoke`

Make an object callable like a function:

```kotlin
class Adder(val amount: Int) {
    operator fun invoke(x: Int) = x + amount
}

val addFive = Adder(5)
addFive(10)            // 15
```

## Comparison with `compareTo`

Implementing `Comparable` (which defines `compareTo`) enables `<`, `>`, `<=`,
`>=`, and sorting:

```kotlin
data class Money(val cents: Int) : Comparable<Money> {
    override fun compareTo(other: Money) = cents.compareTo(other.cents)
}

Money(100) < Money(250)   // true
```

## Destructuring with `componentN`

`data class`es generate `component1()`, `component2()`, … so they can be
destructured. Any type defining these `operator` functions can too:

```kotlin
val (x, y) = Vector(3, 4)   // x = 3, y = 4
```

{: .warning }
Overload operators only when the meaning is obvious. `+` on a `Vector` is clear;
`+` doing something surprising hurts readability.

## Exercises

1. Add a `minus` operator to `Vector`.

2. Give `Money` a `plus` operator and a `compareTo`, then sort a list of `Money`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   data class Money(val cents: Int) : Comparable<Money> {
       operator fun plus(other: Money) = Money(cents + other.cents)
       override fun compareTo(other: Money) = cents.compareTo(other.cents)
   }
   {% endhighlight %}

   </details>

This solution is in `examples/core/part5/` and is tested by CI.

---

Previous: [Delegation]({% link part5/03-delegation.md %}) ·
Next: [Type-Safe Builders (DSLs)]({% link part5/05-dsls.md %})
