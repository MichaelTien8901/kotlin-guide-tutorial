---
layout: default
title: "Advanced Compose"
parent: "Part 8: Advanced Android App Development"
nav_order: 2
---

# Advanced Compose

## State hoisting

A composable that owns no state and receives both its value and a change callback
is **stateless** — easier to reuse, preview, and test. Moving state up to the
caller is **state hoisting**:

```kotlin
// Stateless: state hoisted to the caller
@Composable
fun NameField(value: String, onValueChange: (String) -> Unit) {
    TextField(value = value, onValueChange = onValueChange)
}

// Stateful caller
@Composable
fun NameForm() {
    var name by rememberSaveable { mutableStateOf("") }
    NameField(value = name, onValueChange = { name = it })
}
```

Rule: hoist state to the **lowest common ancestor** that needs it.

## `remember` vs `rememberSaveable`

- `remember` survives recomposition.
- `rememberSaveable` also survives configuration changes (rotation) and process
  death, by saving into the instance state bundle.

## Side effects

Composables should be free of side effects during composition. For effects, use
the dedicated APIs:

| API | Use for |
|-----|---------|
| `LaunchedEffect(key)` | Run a coroutine when entering composition or when `key` changes |
| `rememberCoroutineScope()` | Launch coroutines from callbacks (e.g. `onClick`) |
| `DisposableEffect(key)` | Register/unregister listeners with cleanup |
| `derivedStateOf` | Compute state from other state without extra recompositions |

```kotlin
@Composable
fun Toast(message: String?, onShown: () -> Unit) {
    LaunchedEffect(message) {
        if (message != null) {
            // show...
            onShown()
        }
    }
}
```

## Material 3 theming

Wrap your app in `MaterialTheme` and use `MaterialTheme.colorScheme` /
`typography`:

```kotlin
MaterialTheme(colorScheme = darkColorScheme()) {
    Surface { /* app content */ }
}
```

## Lists and performance

Use `LazyColumn`/`LazyRow` for long lists, and provide stable **keys** so Compose
can track items efficiently:

```kotlin
LazyColumn {
    items(articles, key = { it.id }) { article ->
        ArticleRow(article)
    }
}
```

## Exercises

1. Refactor a stateful composable into a stateless one plus a stateful wrapper.

2. Use `LaunchedEffect` to trigger a one-time load when a screen first appears.

---

Previous: [App Architecture]({% link part8/01-architecture.md %}) ·
Next: [Navigation]({% link part8/03-navigation.md %})
