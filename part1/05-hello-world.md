---
layout: default
title: "Hello, World!"
parent: "Part 1: Getting Started"
nav_order: 5
---

# Hello, World!

Time to write and run your first real Kotlin program.

## The program entry point

Every Kotlin application starts at the `main` function:

```kotlin
fun main() {
    println("Hello, World!")
}
```

- `fun` declares a function.
- `main` is the special name the runtime looks for to start the program.
- `println(...)` prints a line to standard output.
- No class, no `public static void`, no semicolons required.

## Reading program arguments

`main` can optionally receive command-line arguments as an `Array<String>`:

```kotlin
fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "World"
    println("Hello, $name!")
}
```

Run it with an argument and it greets that name; run it with none and it falls
back to `"World"`. Note the **string template** `"$name"`.

## Extracting a function

Pulling the greeting into its own function makes it reusable and testable:

```kotlin
fun greeting(name: String): String = "Hello, $name!"

fun main() {
    println(greeting("Kotlin"))
}
```

`greeting` is a **single-expression function**: when a function just returns one
expression, you can use `=` instead of a body with `return`.

## The runnable example

This program is in the guide's examples as the `hello` module:

```text
examples/core/hello/
├── build.gradle.kts
└── src/
    ├── main/kotlin/examples/hello/Hello.kt
    └── test/kotlin/examples/hello/HelloTest.kt
```

Run it from the `examples/core` directory:

```bash
cd examples/core
./gradlew :hello:run
```

And run its test:

```bash
./gradlew :hello:test
```

{: .tip }
You can also paste any of the snippets above directly into the
[Kotlin Playground]({% link part1/03-kotlin-playground.md %}).

## Exercises

Try these yourself, then expand the solutions. The reference solutions live in
`examples/core/hello/` and are compiled and tested by CI.

1. Write a function `shout(message: String): String` that returns the message in
   uppercase with an exclamation mark appended. For example, `shout("hello")`
   should return `"HELLO!"`.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun shout(message: String): String = "${message.uppercase()}!"
   {% endhighlight %}

   </details>

2. Write a function `greetAll(vararg names: String): String` that returns each
   name greeted with `greeting(...)`, one per line.

   <details>
   <summary>Solution</summary>

   {% highlight kotlin %}
   fun greetAll(vararg names: String): String =
       names.joinToString(separator = "\n") { greeting(it) }
   {% endhighlight %}

   </details>

3. Change `main` so it greets the name passed as the first command-line
   argument, defaulting to `"World"` when no argument is given. (Hint: see
   *Reading program arguments* above.)

---

Previous: [Build Tools & the Compiler]({% link part1/04-build-tools.md %}) ·
Next: [Part 2: Language Basics]({% link part2/index.md %})
