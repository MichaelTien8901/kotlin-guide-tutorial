---
layout: default
title: "Inheritance & Interfaces"
parent: "Part 3: OOP & Functions"
nav_order: 3
---

# Inheritance & Interfaces

## Classes are final by default

To allow subclassing, mark a class `open`. Members you want to override must also
be `open`:

```kotlin
open class Animal(val name: String) {
    open fun sound(): String = "..."
}

class Dog(name: String) : Animal(name) {
    override fun sound(): String = "Woof"
}
```

- `: Animal(name)` calls the superclass constructor.
- `override` is **required** (not optional) when redefining a member.

Call the parent implementation with `super`:

```kotlin
class LoudDog(name: String) : Animal(name) {
    override fun sound(): String = super.sound() + "!!!"
}
```

## Abstract classes

An `abstract` class cannot be instantiated and may leave members unimplemented:

```kotlin
abstract class Shape {
    abstract fun area(): Double           // no body — subclasses must implement
    fun describe() = "Area is ${area()}"   // concrete
}
```

## Interfaces

Interfaces declare behavior and can include default implementations, but hold no
state:

```kotlin
interface Drawable {
    fun draw()                            // abstract
    fun show() = println("drawing")       // default implementation
}

interface Clickable {
    fun onClick()
}

class Button : Drawable, Clickable {       // implement multiple interfaces
    override fun draw() = println("[ Button ]")
    override fun onClick() = println("clicked")
}
```

When two interfaces provide the same default method, disambiguate with
`super<Type>`:

```kotlin
interface A { fun hi() = "A" }
interface B { fun hi() = "B" }
class C : A, B {
    override fun hi() = super<A>.hi() + super<B>.hi()
}
```

## Class vs interface — which?

- Use an **interface** for a capability many unrelated types can have
  (`Drawable`, `Comparable`).
- Use an **abstract class** when subtypes share state or constructor logic.

## Exercises

1. Create an `open class Vehicle` with an `open fun describe()` and subclasses
   `Car` and `Bicycle` that override it.

2. Define an interface `Greeter` with a default `greet()` that returns
   `"Hello"`, and a class that overrides it to return `"Hi"`.

---

Previous: [Visibility & Packages]({% link part3/02-visibility.md %}) ·
Next: [Data, Enum & Sealed Classes]({% link part3/04-data-enum-sealed.md %})
