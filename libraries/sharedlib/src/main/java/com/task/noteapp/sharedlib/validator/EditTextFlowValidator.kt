package com.task.noteapp.sharedlib.validator

import android.widget.EditText
import com.inflow.android.utils.validator.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import reactivecircus.flowbinding.android.widget.textChanges

class EditTextFlowValidator(
    private val editText: EditText
) : Validator() {

    /**
     * It`s return new validation observable, that start validate, after your subscription
     * @return new validation observable
     */
    fun asFlow(): Flow<Boolean> {
        return createFlow(editText)
            .map {
                validate(it, onSuccess = {
                    editText.error = null
                }, onError = { message ->
                    editText.error = message
                })
            }
    }

    companion object {
        /**
         * @return represent EditText TextWatcher as Observable<String>, that emmit elements by onTextChanged
         */
        fun createFlow(editText: EditText): Flow<String> {
            return editText.textChanges()
                .map {
                    it.toString()
                }
                .drop(1)
                .debounce(200)
                .flowOn(Dispatchers.Main)
        }
    }
}
