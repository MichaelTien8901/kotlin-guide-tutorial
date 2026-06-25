package com.example.kotlinguide.articles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/** A two-screen list/detail flow wired with Navigation Compose. */
@Composable
fun ArticlesApp(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ArticleListScreen(onOpen = { id -> navController.navigate("detail/$id") })
        }
        composable("detail/{id}") { entry ->
            val id = entry.arguments?.getString("id")?.toIntOrNull() ?: 0
            ArticleDetailScreen(id = id)
        }
    }
}

@Composable
fun ArticleListScreen(
    onOpen: (Int) -> Unit,
    viewModel: ArticlesViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { viewModel.load() }

    when (val s = state) {
        ArticlesUiState.Loading -> CircularProgressIndicator()
        is ArticlesUiState.Error -> Text("Error: ${s.message}", Modifier.padding(16.dp))
        is ArticlesUiState.Success -> LazyColumn {
            items(s.articles, key = { it.id }) { article ->
                Text(
                    text = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpen(article.id) }
                        .padding(16.dp),
                )
            }
        }
    }
}

@Composable
fun ArticleDetailScreen(id: Int) {
    Text(text = "Article #$id", modifier = Modifier.padding(16.dp))
}
