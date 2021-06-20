package com.inflow.android.utils.validator

open class Validator : LinkedHashSet<ValidateRule>() {

    open fun validate(
        data: String,
        onSuccess: (() -> Unit)? = null,
        onError: ((String) -> Unit)? = null
    ): Boolean {
        Preconditions.atLeastOneNotNull(
            onSuccess,
            onError
        )

        forEach {
            if (!it.validate(data)) {
                onError?.invoke(it.errorMessage())
                return false
            }
        }

        onSuccess?.invoke()
        return true
    }
}
