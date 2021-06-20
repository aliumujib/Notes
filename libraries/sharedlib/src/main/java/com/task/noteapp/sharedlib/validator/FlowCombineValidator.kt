package com.task.noteapp.sharedlib.validator

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine


class FlowCombineValidator(private val flows: List<Flow<Boolean>>) {

    /**
     * Combiner for your validations observable, which allows change UI if all observables emmit <b>true</b>
     * (use-full for change button state)
     */
    fun asFlow(): Flow<Boolean> {
        return combine(flows) { aggregate -> aggregate.all { it } }
    }

    companion object {
        fun create(vararg flows: Flow<Boolean>): Flow<Boolean> {
            return FlowCombineValidator(flows.toList()).asFlow()
        }
    }
}
