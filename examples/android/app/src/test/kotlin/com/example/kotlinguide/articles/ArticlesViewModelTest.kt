package com.example.kotlinguide.articles

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArticlesViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load emits Success with the repository's articles`() = runTest(dispatcher) {
        val vm = ArticlesViewModel(InMemoryArticleRepository(listOf(Article(1, "t", "b"))))
        vm.load()
        advanceUntilIdle()

        val state = vm.uiState.value
        assertTrue(state is ArticlesUiState.Success)
        assertEquals(1, (state as ArticlesUiState.Success).articles.size)
    }
}
