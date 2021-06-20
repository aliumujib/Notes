package com.task.noteapp.sharedlib.validator.rules

import com.inflow.android.utils.validator.ValidateRule

class NotEmptyRule : ValidateRule {

    override fun errorMessage() = "This field can not be empty."

    override fun validate(data: String?): Boolean {
        if (!NotNullRule().validate(data)) return false
        return data!!.isNotEmpty()
    }

}
