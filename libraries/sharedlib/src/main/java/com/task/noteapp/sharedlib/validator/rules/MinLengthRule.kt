package com.task.noteapp.sharedlib.validator.rules

import com.inflow.android.utils.validator.ValidateRule

class MinLengthRule(private val count: Int) :
    ValidateRule {

    override fun validate(data: String?): Boolean {
        if (!NotNullRule().validate(data) && !NotEmptyRule().validate(data)) return false
        return data!!.length >= count
    }

    override fun errorMessage() = "This field must contain at least $count characters"
}
