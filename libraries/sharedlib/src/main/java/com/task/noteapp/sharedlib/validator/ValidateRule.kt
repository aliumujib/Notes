package com.inflow.android.utils.validator

interface ValidateRule {

    /**
     * Method for your validation logic
     */
    fun validate(data: String?): Boolean

    /**
     * The error message that will be sent, if the <b>validate</b> method returns <b>false</b>
     */
    fun errorMessage(): String
}
