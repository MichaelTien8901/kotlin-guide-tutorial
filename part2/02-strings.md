---
layout: default
title: "Strings"
parent: "Part 2: Language Basics"
nav_order: 2
---

# Strings

## String templates

Embed values directly in a string with `$`:

```kotlin
val name = "Kotlin"
val version = 2.0
println("Hello, $name!")              // simple variable
println("Version is ${version + 0.1}") // any expression in ${...}
```

Use `${...}` for expressions; bare `$name` works for a simple variable.

## Escapes and special characters

```kotlin
val tabbed = "name:\tKotlin"
val quote = "She said \"hi\""
val path = "C:\\temp"
val dollar = "\$5.00"   // a literal dollar sign
```

## Multiline (raw) strings

Triple-quoted strings span lines and ignore escaping:

```kotlin
val json = """
    {
        "name": "Kotlin",
        "year": 2016
    }
""".trimIndent()
```

`trimIndent()` removes the common leading whitespace. `trimMargin()` trims up to
a margin prefix (`|` by default).

## Common operations

```kotlin
val s = "Kotlin"
s.length            // 6
s[0]                // 'K'  (indexing)
s.uppercase()       // "KOTLIN"
s.lowercase()       // "kotlin"
s.substring(0, 3)   // "Kot"
s.startsWith("Ko")  // true
s.contains("tli")   // true
s.replace("t", "T") // "KoTlin"
"a,b,c".split(",")  // ["a", "b", "c"]
"  hi  ".trim()     // "hi"
```

## Equality

Use `==` for value equality (it calls `.equals()`), and `===` for reference
identity:

```kotlin
val a = "hi"
val b = "h" + "i"
println(a == b)    // true  (same content)
println(a === b)   // may be false (different objects)
```

{: .tip }
In Kotlin, `==` does what you usually want (content comparison). This is unlike
Java, where `==` compares references for objects.

## Exercises

1. Given `val first = "Ada"` and `val last = "Lovelace"`, build the string
   `"Lovelace, Ada"` using a template.

2. Write `initials(fullName: String): String` that returns the uppercased first
   letter of each space-separated word. For `"ada lovelace"` it returns `"AL"`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun initials(fullName: String): String =
       fullName.split(" ")
           .filter { it.isNotEmpty() }
           .map { it.first().uppercaseChar() }
           .joinToString("")
   {% endhighlight %}

   </details>

---

Previous: [Variables & Types]({% link part2/01-variables-and-types.md %}) ·
Next: [Null Safety]({% link part2/03-null-safety.md %})
