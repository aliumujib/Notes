package com.task.noteapp.sharedlib.validator.rules

import android.content.res.Resources
import com.inflow.android.utils.validator.ValidateRule

abstract class BaseResourcesRule(val resources: Resources) : ValidateRule {

    abstract fun errorMessageResId(): Int

    override fun errorMessage(): String {
        return resources.getString(errorMessageResId())
    }
}
