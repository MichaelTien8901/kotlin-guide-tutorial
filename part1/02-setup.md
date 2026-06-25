---
layout: default
title: "Setting Up Your Environment"
parent: "Part 1: Getting Started"
nav_order: 2
---

# Setting Up Your Environment

You have a few ways to run Kotlin. This chapter sets up a full local
environment. If you just want to try Kotlin without installing anything, skip
ahead to [Kotlin Playground]({% link part1/03-kotlin-playground.md %}).

## What you need

| Tool | Purpose | Recommended |
|------|---------|-------------|
| **JDK** | Runs Kotlin/JVM and Gradle | Temurin (Eclipse Adoptium) **JDK 17** |
| **IntelliJ IDEA** | IDE for general Kotlin | Community Edition (free) |
| **Android Studio** | IDE for Android (Parts 7–9) | Latest stable |

{: .note }
**JDK 17** is the version used by the example projects in this guide. Newer LTS
versions (21+) also work, but 17 is the safe baseline.

## 1. Install the JDK

### Linux

```bash
# Debian/Ubuntu
sudo apt update && sudo apt install temurin-17-jdk
# or with SDKMAN (any distro)
curl -s "https://get.sdkman.io" | bash
sdk install java 17.0.11-tem
```

### macOS

```bash
# Homebrew
brew install --cask temurin@17
```

### Windows

Download the **Temurin 17 (MSI)** installer from
[adoptium.net](https://adoptium.net/) and run it. Accept the option to set
`JAVA_HOME`.

### Verify

```bash
java -version
```

You should see something like `openjdk version "17.x.x"`.

## 2. Install an IDE

### IntelliJ IDEA (general Kotlin)

Download **IntelliJ IDEA Community Edition** from
[jetbrains.com/idea](https://www.jetbrains.com/idea/download/). Kotlin support
is bundled — no plugin needed. On first launch, create a **New Project →
Kotlin** to confirm it works.

### Android Studio (for Parts 7–9)

Download from [developer.android.com/studio](https://developer.android.com/studio).
Android Studio bundles its own JDK and the Android SDK. You can install it now
or wait until you reach [Part 7]({% link part7/index.md %}).

{: .tip }
You can use IntelliJ IDEA for Parts 1–6 and switch to Android Studio for the
Android parts. Both share the same underlying editor, so the experience is
nearly identical.

## 3. (Optional) Command-line compiler

If you prefer the terminal, install the standalone Kotlin compiler:

```bash
# SDKMAN (Linux/macOS)
sdk install kotlin

# Homebrew (macOS)
brew install kotlin
```

Verify with `kotlinc -version`. We use this in
[Build Tools & the Compiler]({% link part1/04-build-tools.md %}).

## Checkpoint

You are ready when:

- `java -version` reports JDK 17 (or newer)
- Your IDE opens and can create a Kotlin project

---

Previous: [Introduction]({% link part1/01-introduction.md %}) ·
Next: [Kotlin Playground]({% link part1/03-kotlin-playground.md %})
