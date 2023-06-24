package com.example.shopify

import com.example.shopify.core.util.ApiState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
suspend fun <T> Flow<T>.getOrAwait(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)

    val job = CoroutineScope(Job() + Dispatchers.Main).launch {
        first {
            data = it
            if (data is ApiState.Success<*>) {
                latch.countDown()
                true
            } else {
                false
            }
        }
    }

    if (!latch.await(time, timeUnit)) {
        job.cancel()
        throw TimeoutException("Value was never emitted.")
    }

    return data as T
}
