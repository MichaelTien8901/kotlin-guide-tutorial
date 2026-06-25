---
layout: default
title: "Background Work with WorkManager"
parent: "Part 8: Advanced Android App Development"
nav_order: 8
---

# Background Work with WorkManager

**WorkManager** runs deferrable, guaranteed background work — syncs, uploads,
periodic cleanup — that should survive app exit and device restart.

{: .note }
WorkManager is shown here as idiomatic code. It needs the Android runtime to
execute, so it is not part of the CI unit-test module.

## Setup

```kotlin
implementation("androidx.work:work-runtime-ktx:2.9.1")
```

## A CoroutineWorker

`CoroutineWorker` lets you write suspending work directly:

```kotlin
class SyncWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = try {
        // suspend work — e.g. repository.sync()
        Result.success()
    } catch (e: Exception) {
        Result.retry()
    }
}
```

Return `Result.success()`, `Result.retry()`, or `Result.failure()`.

## Constraints

Only run when conditions are met (network, charging, idle):

```kotlin
val constraints = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .setRequiresCharging(true)
    .build()
```

## Scheduling

```kotlin
// one-off
val request = OneTimeWorkRequestBuilder<SyncWorker>()
    .setConstraints(constraints)
    .build()
WorkManager.getInstance(context).enqueue(request)

// periodic (minimum interval 15 minutes)
val periodic = PeriodicWorkRequestBuilder<SyncWorker>(1, TimeUnit.HOURS).build()
WorkManager.getInstance(context).enqueueUniquePeriodicWork(
    "sync", ExistingPeriodicWorkPolicy.KEEP, periodic,
)
```

## Observing work state

```kotlin
WorkManager.getInstance(context)
    .getWorkInfoByIdFlow(request.id)
    .collect { info -> /* RUNNING, SUCCEEDED, FAILED ... */ }
```

## When to use what

| Need | Use |
|------|-----|
| Deferrable, guaranteed work | WorkManager |
| Immediate work tied to the UI | `viewModelScope` coroutine |
| Exact-time alarms | `AlarmManager` |

## Exercises

1. Write a `CoroutineWorker` that "uploads" (simulated) and returns `retry` on
   failure.

2. Schedule it to run only when the device is on an unmetered network.

---

Previous: [Dependency Injection with Hilt]({% link part8/07-hilt-di.md %}) ·
Next: [Modularization & Build Variants]({% link part8/09-modularization.md %})
