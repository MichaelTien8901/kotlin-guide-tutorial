package com.example.kotlinguide

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * A minimal ViewModel exposing immutable UI state via StateFlow.
 * The state updates are synchronous, so the logic is testable on the JVM
 * without an emulator (see CounterViewModelTest).
 */
class CounterViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    fun increment() = _count.update { it + 1 }
    fun reset() = _count.update { 0 }
}
