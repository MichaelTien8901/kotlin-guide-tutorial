---
layout: default
title: "Coroutines on Android"
parent: "Part 7: Android Foundations"
nav_order: 5
---

# Coroutines on Android

Android gives coroutines **lifecycle-aware scopes** so async work is cancelled
automatically when the screen goes away — no leaks.

## `viewModelScope`

A `ViewModel` has a built-in `viewModelScope` that is cancelled when the
ViewModel is cleared:

```kotlin
class UserViewModel(private val repo: UserRepository) : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun load() {
        viewModelScope.launch {              // cancelled when the VM is cleared
            _state.value = try {
                UiState.Success(repo.fetch())
            } catch (e: Exception) {
                UiState.Error(e.message ?: "failed")
            }
        }
    }
}
```

## Exposing state with StateFlow

Expose **immutable** `StateFlow` to the UI and keep the `MutableStateFlow`
private. The example app's `CounterViewModel` shows the pattern:

```kotlin
class CounterViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    fun increment() = _count.update { it + 1 }
}
```

Because the state lives in the `ViewModel`, it survives configuration changes
(like rotation) automatically.

## Collecting flows in Compose

Use `collectAsStateWithLifecycle()` so collection pauses when the screen is not
visible:

```kotlin
@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {
    val count by viewModel.count.collectAsStateWithLifecycle()
    Text("Count: $count")
    Button(onClick = { viewModel.increment() }) { Text("Increment") }
}
```

## `lifecycleScope`

Activities and Fragments expose `lifecycleScope` for UI-tied coroutines. Prefer
keeping logic in the `ViewModel`; use `lifecycleScope` for purely view-related
work.

## The runnable example

`examples/android/` contains `CounterViewModel` and `CounterScreen`. The
ViewModel's state logic is covered by a JVM unit test
(`CounterViewModelTest`) that runs in CI — no emulator required:

```kotlin
@Test
fun increment_increasesCount() {
    val vm = CounterViewModel()
    vm.increment(); vm.increment()
    assertEquals(2, vm.count.value)
}
```

## Exercises

1. Add a `decrement()` to `CounterViewModel` and a button for it, then extend the
   unit test.

2. Write a `ViewModel` that exposes a `UiState` `StateFlow` and loads data in
   `viewModelScope`, mapping success/failure to `Success`/`Error`.

---

Previous: [Kotlin Idioms for Android]({% link part7/04-android-kotlin-idioms.md %}) ·
Next: [Part 8: Advanced Android App Development]({% link part8/index.md %})
