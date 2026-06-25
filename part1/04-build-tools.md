---
layout: default
title: "Build Tools & the Compiler"
parent: "Part 1: Getting Started"
nav_order: 4
---

# Build Tools & the Compiler

Real projects are built with **Gradle**, but it helps to understand the compiler
underneath. This chapter covers `kotlinc`, the REPL, and your first Gradle
project.

## The command-line compiler (`kotlinc`)

If you installed `kotlinc` in [Setup]({% link part1/02-setup.md %}), you can
compile and run a file directly.

Create `Hello.kt`:

```kotlin
fun main() {
    println("Compiled with kotlinc")
}
```

Compile to a JAR and run it:

```bash
kotlinc Hello.kt -include-runtime -d hello.jar
java -jar hello.jar
```

Or run a `.kt` file as a script without producing a JAR:

```bash
kotlinc -script script.kts
```

## The REPL

The **REPL** (Read-Eval-Print Loop) lets you evaluate Kotlin interactively —
great for experimenting:

```bash
$ kotlinc
Welcome to Kotlin version 2.0 (...)
>>> val x = 21
>>> x * 2
res1: kotlin.Int = 42
>>> :quit
```

## Your first Gradle project

Gradle automates compiling, testing, packaging, and dependency management. Most
Kotlin and all Android projects use it.

Generate a project skeleton:

```bash
mkdir my-app && cd my-app
gradle init --type kotlin-application --dsl kotlin
```

A typical Gradle Kotlin project looks like this:

```text
my-app/
├── settings.gradle.kts        # project name, module list
├── build.gradle.kts           # plugins & dependencies (root)
├── gradle/
│   └── libs.versions.toml      # version catalog
├── gradlew, gradlew.bat        # the Gradle wrapper (run without installing Gradle)
└── app/
    ├── build.gradle.kts
    └── src/
        ├── main/kotlin/...     # production code
        └── test/kotlin/...     # tests
```

Build and run with the **wrapper** (`./gradlew`), which downloads the exact
Gradle version the project expects:

```bash
./gradlew build      # compile + test
./gradlew run        # run the application
```

{: .note }
Always commit the Gradle wrapper (`gradlew`, `gradlew.bat`,
`gradle/wrapper/`). It lets anyone build the project with the right Gradle
version, without installing Gradle themselves.

## The guide's examples

This guide's runnable code lives in the `examples/` directory as two Gradle
builds: `examples/core/` (plain Kotlin/JVM, JDK 17 only) and
`examples/android/` (the Android apps). Each defines its dependency versions in
a `gradle/libs.versions.toml` **version catalog**. See the
[Examples & Exercises]({% link examples/README.md %}) page for how to build them.

---

Previous: [Kotlin Playground]({% link part1/03-kotlin-playground.md %}) ·
Next: [Hello, World!]({% link part1/05-hello-world.md %})
