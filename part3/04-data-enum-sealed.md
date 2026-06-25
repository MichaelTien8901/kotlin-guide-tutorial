---
layout: default
title: "Data, Enum & Sealed Classes"
parent: "Part 3: OOP & Functions"
nav_order: 4
---

# Data, Enum & Sealed Classes

Kotlin has special class kinds that remove boilerplate for common modeling needs.

## Data classes

A `data class` auto-generates `equals()`, `hashCode()`, `toString()`, `copy()`,
and destructuring components:

```kotlin
data class User(val name: String, val age: Int)

val a = User("Ada", 36)
val b = a.copy(age = 37)            // copy with one field changed
println(a)                         // User(name=Ada, age=36)
println(a == User("Ada", 36))      // true (value equality)

val (name, age) = a                // destructuring
```

Use data classes for values that are defined by their contents (DTOs, results,
records).

## Enum classes

A fixed set of named constants, which can carry data and methods:

```kotlin
enum class Direction(val degrees: Int) {
    NORTH(0), EAST(90), SOUTH(180), WEST(270);

    fun opposite(): Direction = when (this) {
        NORTH -> SOUTH
        SOUTH -> NORTH
        EAST -> WEST
        WEST -> EAST
    }
}

Direction.NORTH.degrees     // 0
Direction.valueOf("EAST")   // Direction.EAST
Direction.entries           // all values
```

## Sealed classes and interfaces

A `sealed` type has a **closed** set of subtypes known at compile time — perfect
for modeling "one of these cases":

```kotlin
sealed interface Shape {
    data class Circle(val radius: Double) : Shape
    data class Rectangle(val width: Double, val height: Double) : Shape
}

fun area(shape: Shape): Double = when (shape) {
    is Shape.Circle -> Math.PI * shape.radius * shape.radius
    is Shape.Rectangle -> shape.width * shape.height
    // no `else` needed — the compiler knows all cases are covered
}
```

Because the compiler knows every subtype, a `when` over a sealed type is
**exhaustive** without an `else`. Add a new subtype and the compiler flags every
`when` you forgot to update — a huge safety win.

{: .tip }
Inside each `is` branch, `shape` is **smart-cast** to that subtype, so you can
access `shape.radius` directly without a manual cast.

## Exercises

1. Model a `Result` sealed interface with `Success(val data: String)` and
   `Failure(val error: String)`, then write a `when` that handles both.

2. Using the `Shape` type above, write `area(shape: Shape): Double`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun area(shape: Shape): Double = when (shape) {
       is Shape.Circle -> Math.PI * shape.radius * shape.radius
       is Shape.Rectangle -> shape.width * shape.height
   }
   {% endhighlight %}

   </details>

This solution is in `examples/core/part3/` and is tested by CI.

---

Previous: [Inheritance & Interfaces]({% link part3/03-inheritance-interfaces.md %}) ·
Next: [Objects & Companions]({% link part3/05-objects.md %})
