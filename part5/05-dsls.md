---
layout: default
title: "Type-Safe Builders (DSLs)"
parent: "Part 5: Functional & Advanced"
nav_order: 5
---

# Type-Safe Builders (DSLs)

Kotlin's lambdas-with-receiver let you build small, readable **domain-specific
languages** — the same technique behind Gradle Kotlin scripts and Jetpack
Compose.

## Lambdas with a receiver

A function type can have a **receiver**: `Type.() -> Unit`. Inside such a lambda,
`this` is the receiver, so you can call its members directly:

```kotlin
fun buildString(block: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.block()              // call the lambda with sb as the receiver
    return sb.toString()
}

val text = buildString {
    append("Hello")        // `this` is the StringBuilder
    append(", world")
}
```

(`buildString` actually exists in the standard library — this shows how it
works.)

## A small HTML DSL

```kotlin
class Tag(val name: String) {
    private val children = mutableListOf<Tag>()
    var text: String = ""

    fun tag(name: String, block: Tag.() -> Unit) {
        children.add(Tag(name).apply(block))
    }

    override fun toString(): String {
        val inner = if (children.isEmpty()) text else children.joinToString("")
        return "<$name>$inner</$name>"
    }
}

fun html(block: Tag.() -> Unit): Tag = Tag("html").apply(block)

val page = html {
    tag("body") {
        tag("p") { text = "Hello DSL" }
    }
}
// <html><body><p>Hello DSL</p></body></html>
```

## `@DslMarker`

In nested builders, `@DslMarker` prevents accidentally calling an outer
receiver's methods from an inner block, catching scoping mistakes at compile
time:

```kotlin
@DslMarker
annotation class HtmlDsl

@HtmlDsl
class Tag(/* ... */)
```

## Why DSLs?

Type-safe builders give you configuration that is **checked by the compiler**,
**autocompleted by the IDE**, and reads like declarative markup — while remaining
ordinary Kotlin.

## Exercises

1. Extend the HTML DSL with an `a(href: String)` tag.

2. Write a `menu { item("A"); item("B") }` builder that collects item names into
   a `List<String>`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   class MenuBuilder {
       val items = mutableListOf<String>()
       fun item(name: String) { items.add(name) }
   }

   fun menu(block: MenuBuilder.() -> Unit): List<String> =
       MenuBuilder().apply(block).items
   {% endhighlight %}

   </details>

This solution is in `examples/core/part5/` and is tested by CI.

---

Previous: [Operator Overloading]({% link part5/04-operators.md %}) ·
Next: [Exceptions & Reflection]({% link part5/06-exceptions-reflection.md %})
