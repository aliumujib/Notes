package com.task.notes.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, T> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * Function which builds Flow instance based on given arguments
     * @param params initial use case arguments
     */
    abstract fun build(params: Params? = null): Flow<T>

    protected fun execute(params: Params? = null): Flow<T> {
        return this.build(params)
            .flowOn(postExecutionThread.io)
    }
}
