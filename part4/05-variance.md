---
layout: default
title: "Variance & Reified Types"
parent: "Part 4: Collections & Generics"
nav_order: 5
---

# Variance & Reified Types

**Variance** answers a subtle question: if `Cat` is a `Animal`, is
`Box<Cat>` a `Box<Animal>`? Kotlin lets you declare the answer with `out` and
`in`.

## Covariance with `out`

`out` means the type parameter is only **produced** (returned), never consumed.
Then `Source<Cat>` *is* a `Source<Animal>`:

```kotlin
interface Source<out T> {
    fun next(): T          // T only appears in output position
}

val cats: Source<Cat> = ...
val animals: Source<Animal> = cats   // OK because of `out`
```

Kotlin's read-only `List<out E>` is covariant, which is why a `List<String>` can
be used where a `List<Any>` is expected.

## Contravariance with `in`

`in` means the type parameter is only **consumed** (passed in), never produced.
Then `Sink<Animal>` *is* a `Sink<Cat>`:

```kotlin
interface Sink<in T> {
    fun put(item: T)       // T only appears in input position
}

val anySink: Sink<Animal> = ...
val catSink: Sink<Cat> = anySink     // OK because of `in`
```

{: .tip }
Remember **PECS**: Producer-`out`, Consumer-`in`. If a generic type only hands
values out, mark it `out`; if it only takes values in, mark it `in`.

## Use-site variance (star projection)

When you do not care about the exact type argument, use `*`:

```kotlin
fun printAll(list: List<*>) {        // a List of something
    list.forEach { println(it) }
}
```

## `reified` type parameters

Normally generic types are erased at runtime, so you cannot write `T::class` or
`is T`. An `inline` function with a `reified` type parameter keeps the type
available:

```kotlin
inline fun <reified T> List<*>.firstOfType(): T? =
    firstOrNull { it is T } as? T

val mixed = listOf(1, "two", 3.0, "four")
mixed.firstOfType<String>()   // "two"
```

Because the function is `inline`, the compiler substitutes the real type, so `is
T` works.

## Exercises

1. Explain why `MutableList<T>` cannot be marked `out` (hint: it both produces
   and consumes `T`).

2. Write `inline fun <reified T> Iterable<*>.countOfType(): Int` returning how
   many elements are instances of `T`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   inline fun <reified T> Iterable<*>.countOfType(): Int =
       count { it is T }
   {% endhighlight %}

   </details>

This solution is in `examples/core/part4/` and is tested by CI.

---

Previous: [Generics]({% link part4/04-generics.md %}) ·
Next: [Part 5: Functional & Advanced]({% link part5/index.md %})
