package com.task.notes.utils

import kotlinx.coroutines.withContext

abstract class SuspendUseCase<in P, R>(
    private val postExecutionThread: PostExecutionThread
) {

    suspend operator fun invoke(params: P? = null): R {
        return withContext(postExecutionThread.io) {
            execute(params)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: P?): R

}
