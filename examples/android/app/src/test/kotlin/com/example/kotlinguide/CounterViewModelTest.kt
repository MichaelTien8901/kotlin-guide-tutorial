package com.example.kotlinguide

import org.junit.Assert.assertEquals
import org.junit.Test

/** Local unit test for the ViewModel state logic (no emulator needed). */
class CounterViewModelTest {
    @Test
    fun increment_increasesCount() {
        val vm = CounterViewModel()
        assertEquals(0, vm.count.value)
        vm.increment()
        vm.increment()
        assertEquals(2, vm.count.value)
    }

    @Test
    fun reset_setsCountToZero() {
        val vm = CounterViewModel()
        vm.increment()
        vm.reset()
        assertEquals(0, vm.count.value)
    }
}
