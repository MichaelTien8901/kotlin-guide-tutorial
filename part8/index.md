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
| Architecture | MVVM + UDF / Clean Architecture layers |
| Advanced Compose | State hoisting, side effects, Material 3 |
| Navigation | Compose Navigation, multi-screen flows |
| ViewModel & State | `ViewModel` + `StateFlow`, immutable UI state |
| Room | Entities, DAOs, migrations, queries as `Flow` |
| Networking | Retrofit + `kotlinx.serialization`, DTO mapping |
| Hilt DI | Modules, scopes, injecting ViewModels |
| WorkManager | `CoroutineWorker`, constraints, scheduling |
| Modularization | Gradle modules, build variants, version catalog |
| Testing | Unit, coroutine, and Compose UI tests |
| Performance | Recomposition, leaks, best-practices checklist |

{: .important }
This part requires Android Studio. Instrumented (emulator) tests are documented
as run-locally; unit and Compose UI tests run in CI.

{: .note }
Chapters for this part are being written. Check back soon.
