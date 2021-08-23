package com.antsfamily.rickandmortydata.domain

import kotlinx.coroutines.*
import java.lang.Exception

abstract class BaseUseCase<in Params, out Type> where Type : Any {

    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.IO

    abstract suspend fun run(params: Params): Type

    operator fun invoke(
        params: Params,
        onResult: (Type) -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        val job = CoroutineScope(backgroundDispatcher).async { run(params) }
        CoroutineScope(mainDispatcher).launch {
            try {
                val result = job.await()
                onResult(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}