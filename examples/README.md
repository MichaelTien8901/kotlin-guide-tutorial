---
layout: default
title: "Examples & Exercises"
nav_order: 12
permalink: /examples/
---

# Examples & Exercises

All runnable code for the guide lives here. Examples are split into two
independent Gradle builds so you only need the Android SDK for the Android
parts:

| Build | Path | Needs | Covers |
|-------|------|-------|--------|
| Core (JVM) | `examples/core/` | JDK 17 only | Parts 1–6 and the CLI capstone |
| Android | `examples/android/` | JDK 17 + Android SDK | Parts 7–9 |

Dependency versions are centralized per build in `gradle/libs.versions.toml`
(a Gradle version catalog). The Gradle wrapper is committed, so you do not need
Gradle installed.

## Building

```bash
# Core (JVM) examples
cd examples/core
./gradlew build          # compiles every example and runs its unit tests
./gradlew :hello:run     # run a single example

# Android examples
cd examples/android
./gradlew :app:assembleDebug          # build the app
./gradlew :app:testDebugUnitTest      # run JVM unit tests
```

{: .note }
**CI coverage.** The `Build examples` workflow builds the site, the core
examples, and the Android examples (unit tests). Instrumented/emulator tests
are **run locally only** and are not executed in CI — chapters that include
them say so explicitly.

## Exercise & solution convention

Each chapter ends with an `## Exercises` section. Solutions are provided two
ways so you can check your work without spoilers:

1. **Inline, collapsible answer** in the chapter (Markdown source shown below):

````markdown
## Exercises

1. Write a function `isPalindrome(s: String): Boolean`.

<details>
<summary>Solution</summary>

```kotlin
fun isPalindrome(s: String): Boolean = s == s.reversed()
```

</details>
````

2. **A compiled solution file** under the part's example module, in a
   `solutions/` source set or package (e.g.
   `examples/core/<part>/src/test/kotlin/.../solutions/`). Solution code is
   compiled and tested by CI, so every published answer is guaranteed to work.

When you add an exercise, add its solution in **both** places.
