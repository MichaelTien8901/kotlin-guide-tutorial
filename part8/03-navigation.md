---
layout: default
title: "Navigation"
parent: "Part 8: Advanced Android App Development"
nav_order: 3
---

# Navigation

**Navigation Compose** moves between screens within a single Activity using a
`NavHost` and a `NavController`.

## Dependency

```kotlin
implementation("androidx.navigation:navigation-compose:2.8.x")
```

## A NavHost

Each destination is a `composable` with a string route:

```kotlin
@Composable
fun AppNav(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ArticleListScreen(
                onOpen = { id -> navController.navigate("detail/$id") }
            )
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            ArticleDetailScreen(id = id)
        }
    }
}
```

## Navigating

```kotlin
navController.navigate("detail/42")     // go to a destination
navController.popBackStack()            // go back
navController.navigate("list") {
    popUpTo("list") { inclusive = true } // clear back stack
}
```

## Passing arguments

Arguments are encoded in the route (`detail/{id}`) and read from the back stack
entry. For typed arguments, declare them with `navArgument`:

```kotlin
composable(
    route = "detail/{id}",
    arguments = listOf(navArgument("id") { type = NavType.IntType })
) { entry ->
    val id = entry.arguments?.getInt("id") ?: 0
    ArticleDetailScreen(id = id)
}
```

## Keep navigation in the UI layer

Pass lambdas like `onOpen: (Int) -> Unit` down to screens instead of handing them
the `NavController`. Screens stay reusable and testable, and only `AppNav` knows
the routes.

{: .tip }
The example app wires a two-screen list/detail flow this way, so you can see
state hoisting and navigation working together.

## Exercises

1. Add a third destination (e.g. "about") and a button that navigates to it.

2. Change the detail route to pass a typed `Int` id with `navArgument`.

---

Previous: [Advanced Compose]({% link part8/02-compose-advanced.md %}) ·
Next: [ViewModel & State Management]({% link part8/04-viewmodel-state.md %})
