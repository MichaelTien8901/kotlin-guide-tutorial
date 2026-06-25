---
layout: default
title: "Performance & Best Practices"
parent: "Part 8: Advanced Android App Development"
nav_order: 11
---

# Performance & Best Practices

A checklist for keeping an advanced Compose app fast and correct.

## Compose performance

- **Minimize recomposition scope.** Read state as low in the tree as possible so
  only small composables recompose.
- **Use stable types.** Prefer immutable `data class`es and `List` (not
  `MutableList`) as composable parameters; unstable types force recomposition.
- **Key your lists.** Provide `key = { it.id }` in `LazyColumn` `items`.
- **Hoist `remember` correctly.** Expensive computations go in
  `remember(key) { ... }`, not in the composable body.
- **Avoid backwards writes.** Do not write to state you have already read in the
  same composition.
- **`derivedStateOf`** for state computed from other state, to avoid extra
  recompositions.

## Threading

- Never block the main thread. Wrap blocking I/O in
  `withContext(Dispatchers.IO)`.
- Use `viewModelScope` / `lifecycleScope` so work is cancelled with its owner.
- Collect flows with `collectAsStateWithLifecycle()` so collection stops when the
  UI is not visible.

## Memory and leaks

- Do not hold `Context`/`Activity`/`View` references in a `ViewModel`.
- Cancel coroutines and unregister listeners (`DisposableEffect`) when scopes
  end.
- Prefer `applicationContext` for long-lived needs.

## App size and startup

- Enable R8/minification and resource shrinking for release builds.
- Defer heavy initialization off the startup path.

## A pre-release checklist

- [ ] No blocking work on the main thread
- [ ] Lists use stable keys and items are stable
- [ ] State is hoisted; recomposition scopes are small
- [ ] ViewModels hold no `View`/`Context`
- [ ] Coroutines use lifecycle-aware scopes
- [ ] Release build minified and tested
- [ ] Unit + Compose tests green in CI

## The worked example

`examples/android/` brings the architecture together: a `ViewModel` exposing an
immutable `StateFlow` UI state, a repository behind an interface, navigation, and
JVM unit tests — all building in CI. The Room, Hilt, Retrofit, and WorkManager
chapters above provide drop-in code to extend it.

## Exercises

1. Audit a screen for unnecessary recompositions (use the Layout Inspector /
   recomposition counts).

2. Move a blocking call into `withContext(Dispatchers.IO)` and confirm the UI
   stays responsive.

---

Previous: [Testing]({% link part8/10-testing.md %}) ·
Next: [Part 9: Real-World Projects]({% link part9/index.md %})
