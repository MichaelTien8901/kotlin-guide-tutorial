package com.example.kotlinguide

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Compose UI test that runs on the JVM via Robolectric — no emulator required,
 * so it executes in CI as part of `testDebugUnitTest`.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class CounterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickingIncrement_incrementsTheDisplayedCount() {
        composeTestRule.setContent {
            CounterScreen(viewModel = CounterViewModel())
        }

        composeTestRule.onNodeWithText("Count: 0").assertIsDisplayed()
        composeTestRule.onNodeWithText("Increment").performClick()
        composeTestRule.onNodeWithText("Count: 1").assertIsDisplayed()
    }

    @Test
    fun clickingReset_returnsCountToZero() {
        composeTestRule.setContent {
            CounterScreen(viewModel = CounterViewModel())
        }

        composeTestRule.onNodeWithText("Increment").performClick()
        composeTestRule.onNodeWithText("Reset").performClick()
        composeTestRule.onNodeWithText("Count: 0").assertIsDisplayed()
    }
}
