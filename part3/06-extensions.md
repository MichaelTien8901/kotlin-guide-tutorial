---
layout: default
title: "Extension Functions"
parent: "Part 3: OOP & Functions"
nav_order: 6
---

# Extension Functions

Extensions let you add functions and properties to a type **without modifying its
source** — even types you do not own, like `String` or `List`.

## Extension functions

```kotlin
fun String.isPalindrome(): Boolean {
    val clean = lowercase().filter { it.isLetterOrDigit() }
    return clean == clean.reversed()
}

"Level".isPalindrome()        // true
"hello".isPalindrome()        // false
```

Inside the function, `this` refers to the receiver (the `String`). You can omit
`this`, as with `lowercase()` above.

## Extension properties

```kotlin
val String.firstWord: String
    get() = substringBefore(" ")

"hello world".firstWord       // "hello"
```

Extension properties cannot have backing fields, so they must be computed.

## `infix` functions

An `infix` function can be called without the dot and parentheses:

```kotlin
infix fun Int.clampedTo(max: Int): Int = if (this > max) max else this

10 clampedTo 5                // 5
3 clampedTo 5                 // 3
```

(`to`, used to build map pairs, is itself an infix extension.)

## `vararg` and local helpers, revisited

Extensions combine well with other features:

```kotlin
fun <T> List<T>.second(): T = this[1]

fun StringBuilder.appendLineIf(condition: Boolean, line: String) {
    if (condition) appendLine(line)
}
```

## How extensions work

Extensions are resolved **statically** by the declared type of the receiver —
they are syntactic sugar for a function that takes the receiver as its first
argument. They do not actually modify the class or allow overriding its members.

## Exercises

1. Write an extension `Int.isEven(): Boolean`.

2. Write `String.isPalindrome(): Boolean` that ignores case and non-letters
   (so `"A man, a plan, a canal: Panama"` is a palindrome).

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun String.isPalindrome(): Boolean {
       val clean = lowercase().filter { it.isLetterOrDigit() }
       return clean == clean.reversed()
   }
   {% endhighlight %}

   </details>

This solution is in `examples/core/part3/` and is tested by CI.

---

Previous: [Objects & Companions]({% link part3/05-objects.md %}) ·
Next: [Part 4: Collections & Generics]({% link part4/index.md %})
