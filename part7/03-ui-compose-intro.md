---
layout: default
title: "UI & Jetpack Compose"
parent: "Part 7: Android Foundations"
nav_order: 3
---

# UI & Jetpack Compose

Android has two UI toolkits: the older **View system** (XML layouts) and the
modern **Jetpack Compose** (declarative Kotlin). New apps should use Compose, and
this guide focuses on it.

## Composable functions

A `@Composable` function describes UI. It is called to produce UI and re-called
("recomposed") when its inputs change:

```kotlin
@Composable
fun Greeting(name: String) {
    Text(text = "Hello, $name!")
}
```

Composables are just functions: compose them by calling them.

```kotlin
@Composable
fun Profile() {
    Column {
        Greeting("Ada")
        Text("Welcome back")
    }
}
```

## State and recomposition

UI that changes needs **state**. `remember` keeps a value across recompositions;
`mutableStateOf` makes it observable, so changing it triggers recomposition:

```kotlin
@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }
    Button(onClick = { count++ }) {
        Text("Clicked $count times")
    }
}
```

When `count` changes, Compose re-runs `Counter` and updates only what changed.

## Layout and modifiers

`Column`, `Row`, and `Box` arrange children. `Modifier` adjusts size, padding,
clicks, and more:

```kotlin
Column(
    modifier = Modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
) {
    Text("Title")
    Button(onClick = { /* ... */ }) { Text("Go") }
}
```

## Previews

`@Preview` renders a composable in Android Studio without running the app:

```kotlin
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme { Greeting("Kotlin") }
}
```

## The example

`examples/android/` contains `CounterScreen`, which collects state from a
`ViewModel`. We cover that connection in
[Coroutines on Android]({% link part7/05-coroutines-on-android.md %}).

## Exercises

1. Build a composable that shows a name and a button that appends `"!"` to a
   greeting each click, using `remember`/`mutableStateOf`.

2. Add a `@Preview` for it.

---

Previous: [Activity Lifecycle]({% link part7/02-lifecycle.md %}) ·
Next: [Kotlin Idioms for Android]({% link part7/04-android-kotlin-idioms.md %})
