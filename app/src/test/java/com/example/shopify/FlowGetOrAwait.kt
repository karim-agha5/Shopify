package com.example.shopify

import androidx.annotation.VisibleForTesting
import com.example.shopify.core.util.ApiState
import com.example.shopify.core.util.ApiState2
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
suspend fun <T> Flow<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)

    val collector = object : FlowCollector<T> {
        override suspend fun emit(value: T) {
            if (value !is ApiState.Loading) {
                data = value
                latch.countDown()
            }
        }
    }

    this.onEach { collector.emit(it) }
        .launchIn(GlobalScope)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the StateFlow is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("StateFlow value was never set.")
        }

    } finally {
        // Cancel the flow to prevent leaks
        this.onEach { }.launchIn(GlobalScope)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

