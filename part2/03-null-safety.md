---
layout: default
title: "Null Safety"
parent: "Part 2: Language Basics"
nav_order: 3
---

# Null Safety

One of Kotlin's headline features: the type system distinguishes values that can
be `null` from those that cannot, eliminating most `NullPointerException`s at
**compile time**.

## Nullable vs non-null types

```kotlin
var name: String = "Kotlin"
// name = null            // compile error: String cannot hold null

var nickname: String? = "Kot"  // String? CAN hold null
nickname = null                // OK
```

A `?` after the type makes it nullable. Without it, the value is guaranteed
non-null.

## Safe calls `?.`

Call a member only if the receiver is non-null; otherwise the whole expression
is `null`:

```kotlin
val length: Int? = nickname?.length   // null if nickname is null
```

## The Elvis operator `?:`

Provide a fallback when the left side is `null`:

```kotlin
val len = nickname?.length ?: 0       // 0 if nickname is null
val n = nickname ?: "unknown"
```

## The not-null assertion `!!`

`!!` forces a nullable into a non-null, throwing a `NullPointerException` if it
is actually `null`:

```kotlin
val forced = nickname!!.length        // throws if nickname is null
```

{: .warning }
Avoid `!!` unless you can guarantee the value is non-null. It throws away the
safety the type system gives you. Prefer `?.` and `?:`.

## Safe casts `as?`

```kotlin
val obj: Any = "hello"
val str: String? = obj as? String     // null if the cast would fail
```

## `let` for null handling

Run a block only when a value is non-null:

```kotlin
nickname?.let { value ->
    println("Nickname is $value")      // runs only if non-null
}
```

## Platform types (Java interop)

Values coming from Java have unknown nullability ("platform types"). Treat them
carefully and annotate or wrap them as nullable when in doubt — more on this in
[Part 7]({% link part7/index.md %}).

## Exercises

1. Given `val middle: String? = null`, print its length or `0` using the Elvis
   operator.

2. Write `lengthOrZero(s: String?): Int` that returns the string's length, or `0`
   when it is null — without using `if`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun lengthOrZero(s: String?): Int = s?.length ?: 0
   {% endhighlight %}

   </details>

---

Previous: [Strings]({% link part2/02-strings.md %}) ·
Next: [Control Flow]({% link part2/04-control-flow.md %})
