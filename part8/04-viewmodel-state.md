---
layout: default
title: "ViewModel & State Management"
parent: "Part 8: Advanced Android App Development"
nav_order: 4
---

# ViewModel & State Management

## Immutable UI state

Model a screen's entire state as a single immutable value — often a sealed
interface for mutually-exclusive states, or a data class for a form:

```kotlin
sealed interface ArticlesUiState {
    data object Loading : ArticlesUiState
    data class Success(val articles: List<Article>) : ArticlesUiState
    data class Error(val message: String) : ArticlesUiState
}
```

## Exposing state from the ViewModel

Keep the `MutableStateFlow` private; expose a read-only `StateFlow`:

```kotlin
class ArticlesViewModel(
    private val repository: ArticleRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ArticlesUiState>(ArticlesUiState.Loading)
    val uiState: StateFlow<ArticlesUiState> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _uiState.value = try {
                ArticlesUiState.Success(repository.articles())
            } catch (e: Exception) {
                ArticlesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
```

This is exactly the ViewModel in `examples/android/`, unit-tested in CI.

## Collecting in the UI

```kotlin
@Composable
fun ArticlesScreen(viewModel: ArticlesViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    when (val s = state) {
        ArticlesUiState.Loading -> CircularProgressIndicator()
        is ArticlesUiState.Success -> ArticleList(s.articles)
        is ArticlesUiState.Error -> ErrorText(s.message)
    }
}
```

## Surviving configuration changes

A `ViewModel` outlives Activity recreation, so the loaded state is **not** lost on
rotation. Use `SavedStateHandle` for state that must also survive process death:

```kotlin
class SearchViewModel(private val handle: SavedStateHandle) : ViewModel() {
    var query: String
        get() = handle["query"] ?: ""
        set(value) { handle["query"] = value }
}
```

## `stateIn` for derived flows

Turn a cold flow (e.g. a Room query) into a `StateFlow` for the UI:

```kotlin
val uiState: StateFlow<ArticlesUiState> =
    repository.articlesFlow()
        .map { ArticlesUiState.Success(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ArticlesUiState.Loading)
```

## Exercises

1. Add a `refresh()` to `ArticlesViewModel` that re-runs `load()`.

2. Convert a `Flow<List<Article>>` into a `StateFlow<ArticlesUiState>` with
   `stateIn`.

---

Previous: [Navigation]({% link part8/03-navigation.md %}) ·
Next: [Local Persistence with Room]({% link part8/05-room.md %})
