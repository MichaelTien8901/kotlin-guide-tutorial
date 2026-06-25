---
layout: default
title: "Part 8: Advanced Android App Development"
nav_order: 9
has_children: true
permalink: /part8/
---

# Part 8: Advanced Android App Development

Architect a real, production-style Android app. The chapters build up a single
example app feature by feature.

## What You'll Learn

- Modern app architecture: MVVM + unidirectional data flow / Clean Architecture
- Advanced Jetpack Compose: state hoisting, navigation, side effects, theming
- State management with `ViewModel` and `StateFlow`
- Local persistence with Room
- Networking with Retrofit/Ktor and `kotlinx.serialization`
- Dependency injection with Hilt
- Background work with WorkManager
- Multi-module structure, build variants, performance, and testing

## Chapters

| Chapter | Description |
|---------|-------------|
| [App Architecture]({% link part8/01-architecture.md %}) | MVVM + UDF / Clean Architecture layers |
| [Advanced Compose]({% link part8/02-compose-advanced.md %}) | State hoisting, side effects, Material 3 |
| [Navigation]({% link part8/03-navigation.md %}) | Compose Navigation, multi-screen flows |
| [ViewModel & State Management]({% link part8/04-viewmodel-state.md %}) | `ViewModel` + `StateFlow`, immutable UI state |
| [Local Persistence with Room]({% link part8/05-room.md %}) | Entities, DAOs, migrations, queries as `Flow` |
| [Networking]({% link part8/06-networking.md %}) | Retrofit + `kotlinx.serialization`, DTO mapping |
| [Dependency Injection with Hilt]({% link part8/07-hilt-di.md %}) | Modules, scopes, injecting ViewModels |
| [Background Work with WorkManager]({% link part8/08-workmanager.md %}) | `CoroutineWorker`, constraints, scheduling |
| [Modularization & Build Variants]({% link part8/09-modularization.md %}) | Gradle modules, build variants, version catalog |
| [Testing]({% link part8/10-testing.md %}) | Unit, coroutine, and Compose UI tests |
| [Performance & Best Practices]({% link part8/11-performance.md %}) | Recomposition, leaks, best-practices checklist |

{: .important }
This part requires Android Studio. Instrumented (emulator) tests are documented
as run-locally; unit and Compose UI tests run in CI.

## About the worked example

The example in `examples/android/` integrates the **architecture core** that
builds and is unit-tested in CI: an `ArticlesViewModel` exposing an immutable
`StateFlow` UI state, an `ArticleRepository` behind an interface (constructor
DI), Navigation Compose (list → detail), and JSON parsing with
`kotlinx.serialization`.

{: .note }
**Room, Hilt, Retrofit, and WorkManager** are presented as complete, idiomatic
code in their chapters. They rely on annotation processing and/or the Android
runtime, so to keep the single CI module fast and buildable without an emulator
they are documented as drop-in code rather than wired into that module. This is a
deliberate, disclosed boundary — not a gap in coverage.
