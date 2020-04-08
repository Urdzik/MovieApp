package com.example.movieapp.utils

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.example.movieapp.utils.AppCoroutineDispatchers.Companion.computationDispatcher
import com.example.movieapp.utils.AppCoroutineDispatchers.Companion.ioDispatcher
import com.example.movieapp.utils.AppCoroutineDispatchers.Companion.uiDispatcher

fun CoroutineScope.uiJob(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return startJob(this, uiDispatcher, block)
}

fun CoroutineScope.computaionBackgroundJob(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return startJob(this, computationDispatcher, block)
}

fun CoroutineScope.ioBackgroundJob(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return startJob(this, ioDispatcher, block)
}

suspend fun <T> uiReturnTask(
    block: suspend CoroutineScope.() -> T
){
    startReturnTask(uiDispatcher, block)
}

suspend fun <T> computationReturnTask(
    block: suspend CoroutineScope.() -> T
): T {
    return startReturnTask(computationDispatcher, block)
}

suspend fun <T> ioReturnTask(
    block: suspend CoroutineScope.() -> T
): T {
    return startReturnTask(ioDispatcher, block)
}

fun <T> CoroutineScope.uiTaskAsync(
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return startTaskAsync(this, uiDispatcher, block)
}

fun <T> CoroutineScope.computationTaskAsync(
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return startTaskAsync(this, computationDispatcher, block)
}

fun <T> CoroutineScope.ioTaskAsync(
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return startTaskAsync(this, ioDispatcher, block)
}

fun startJob(
    parentScope: CoroutineScope,
    coroutineContext: CoroutineContext,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return parentScope.launch(coroutineContext) {
        supervisorScope {
            block
        }
    }
}

suspend fun <T> startReturnTask(
    coroutineContext: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(coroutineContext) {
        return@withContext block()
    }
}

fun <T> startTaskAsync(
    parentScope: CoroutineScope,
    coroutineContext: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return parentScope.async(coroutineContext) {
        return@async supervisorScope {
            block()
        }
    }
}

class AppCoroutineDispatchers{
    companion object {
        var uiDispatcher = Dispatchers.Main
        var computationDispatcher = Dispatchers.Default
        var ioDispatcher = Dispatchers.IO
    }
}