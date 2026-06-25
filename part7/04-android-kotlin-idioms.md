---
layout: default
title: "Kotlin Idioms for Android"
parent: "Part 7: Android Foundations"
nav_order: 4
---

# Kotlin Idioms for Android

Kotlin smooths over many rough edges of the Android Java APIs. Here are the
idioms you will use constantly.

## Platform types and null safety

Values returned from Java/Android APIs have **platform types** — Kotlin does not
know whether they are nullable. The compiler lets you treat them as either, but a
wrong assumption crashes at runtime:

```kotlin
val extras = intent.extras           // platform type Bundle!
val name = extras?.getString("name") // be defensive: treat as nullable
    ?: "guest"
```

{: .warning }
When calling Android/Java APIs, assume return values can be null unless the docs
or annotations say otherwise. Use `?.` and `?:` at the boundary.

## Scope functions for setup

`apply` and `also` make object configuration concise:

```kotlin
val intent = Intent(this, DetailActivity::class.java).apply {
    putExtra("id", 42)
    flags = Intent.FLAG_ACTIVITY_NEW_TASK
}
```

## Concise click and callbacks

Lambdas replace anonymous inner classes:

```kotlin
button.setOnClickListener { viewModel.submit() }
```

In Compose this is even simpler — `onClick = { ... }`.

## Extension functions for boilerplate

Add helpers to framework types:

```kotlin
fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

// usage inside an Activity/Context
toast("Saved")
```

## `when` over sealed UI state

Model screen state as a sealed type and render exhaustively:

```kotlin
sealed interface UiState {
    data object Loading : UiState
    data class Success(val items: List<String>) : UiState
    data class Error(val message: String) : UiState
}

@Composable
fun Screen(state: UiState) = when (state) {
    UiState.Loading -> Spinner()
    is UiState.Success -> ItemList(state.items)
    is UiState.Error -> ErrorBanner(state.message)
}
```

This pattern — a sealed `UiState` rendered by an exhaustive `when` — is the
backbone of the architecture in [Part 8]({% link part8/index.md %}).

## Exercises

1. Write a `Context.toast(...)` extension and use it.

2. Model a `UiState` sealed interface with `Loading`, `Success`, and `Error`, and
   write an exhaustive `when` over it.

---

Previous: [UI & Jetpack Compose]({% link part7/03-ui-compose-intro.md %}) ·
Next: [Coroutines on Android]({% link part7/05-coroutines-on-android.md %})
