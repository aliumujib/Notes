package com.task.noteapp.sharedlib.ext

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
fun <T> MutableStateFlow<T>.updateValue(updateFn: T.() -> T): T {
    val updatedValue = updateFn(this.value)
    this.value = updatedValue
    return updatedValue
}


fun <E> HashSet<E>.addAll(vararg item: E) {
    addAll(item.toList())
}

