---
layout: default
title: "Kotlin Playground"
parent: "Part 1: Getting Started"
nav_order: 3
---

# Kotlin Playground

The fastest way to run Kotlin is the **Kotlin Playground** — a browser-based
editor that compiles and runs code on JetBrains' servers. No installation
required.

Open [play.kotlinlang.org](https://play.kotlinlang.org/) and you will see an
editor with a green **Run** button.

## Your first run

Paste this in and press **Run**:

```kotlin
fun main() {
    println("Hello from the Playground!")
}
```

The output appears below the editor.

## What the Playground is good for

| Good for | Not ideal for |
|----------|---------------|
| Trying a snippet quickly | Multi-file projects |
| Sharing runnable examples (each has a URL) | Adding third-party libraries |
| Following along with Parts 1–6 | Android apps (use Android Studio) |
| Experimenting with syntax | Long-running or interactive programs |

## Useful features

- **Target selector** — run on the JVM, JS, or Canvas.
- **Kotlin version** — pick the language version to test new features.
- **Share** — produces a permalink to your snippet.
- **Examples** — the menu includes runnable language examples.

{: .tip }
Every standalone Kotlin snippet in this guide can be pasted directly into the
Playground. For chapters with full projects (especially Android), you will use a
real IDE instead.

{: .note }
The Playground runs each snippet in a sandbox, so it cannot read files, open
network connections, or keep state between runs.

---

Previous: [Setting Up Your Environment]({% link part1/02-setup.md %}) ·
Next: [Build Tools & the Compiler]({% link part1/04-build-tools.md %})
