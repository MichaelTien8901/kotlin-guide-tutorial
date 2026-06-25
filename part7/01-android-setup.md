---
layout: default
title: "Android Setup & App Anatomy"
parent: "Part 7: Android Foundations"
nav_order: 1
---

# Android Setup & App Anatomy

{: .important }
This part requires **Android Studio** and either an emulator or a physical
device. The example project lives in `examples/android/`.

## Create a project

1. Install [Android Studio](https://developer.android.com/studio).
2. **New Project → Empty Activity** (Compose).
3. Set a package name (e.g. `com.example.kotlinguide`) and **Kotlin** as the
   language.
4. Let Gradle sync finish.

## Project anatomy

```text
app/
├── build.gradle.kts             # module config: SDK versions, dependencies
├── src/main/
│   ├── AndroidManifest.xml      # components, permissions, app metadata
│   ├── kotlin/.../MainActivity.kt
│   └── res/                     # resources: strings, themes, drawables
│       └── values/
│           ├── strings.xml
│           └── themes.xml
build.gradle.kts                 # root build (plugin versions)
settings.gradle.kts              # modules + repositories
gradle/libs.versions.toml        # version catalog
```

Key files:

| File | Purpose |
|------|---------|
| `AndroidManifest.xml` | Declares activities, permissions, and the launcher entry point |
| `build.gradle.kts` (app) | `compileSdk`, `minSdk`, dependencies, build features |
| `res/` | Strings, themes, images — referenced as `R.string.app_name` |
| `MainActivity.kt` | The entry-point screen |

## Running

Pick a device in the toolbar (create an emulator via **Device Manager** if
needed) and press **Run** (▶). Android Studio builds the APK, installs it, and
launches the app.

From the command line (in `examples/android/`):

```bash
./gradlew :app:assembleDebug          # build the debug APK
./gradlew :app:installDebug           # install on a running device/emulator
```

{: .note }
`assembleDebug` and unit tests run in CI. Installing and instrumented
(emulator) tests are run locally — they need a device.

## The minimal app

The example's `MainActivity` sets Compose content:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface { CounterScreen() }
            }
        }
    }
}
```

We will unpack `CounterScreen` and `setContent` in the
[UI & Jetpack Compose]({% link part7/03-ui-compose-intro.md %}) chapter.

## Exercises

1. Create and run the Empty Activity project on an emulator.

2. Change the launcher label in `res/values/strings.xml` and confirm it updates.

---

Next: [Activity Lifecycle]({% link part7/02-lifecycle.md %})
