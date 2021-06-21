package com.task.noteapp.sharedlib.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
fun <T> MutableStateFlow<T>.updateValue(updateFn: T.() -> T): T {
    val updatedValue = updateFn(this.value)
    this.value = updatedValue
    return updatedValue
}

fun ViewModel.getViewModelScope(coroutineScope: CoroutineScope?) =
    coroutineScope ?: this.viewModelScope
