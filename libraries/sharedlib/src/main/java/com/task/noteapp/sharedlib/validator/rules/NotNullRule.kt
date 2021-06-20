package com.task.noteapp.sharedlib.validator.rules

import com.inflow.android.utils.validator.ValidateRule

class NotNullRule : ValidateRule {

    override fun errorMessage() = "This field cannot be empty"

    override fun validate(data: String?): Boolean {
        return data != null
    }
}
