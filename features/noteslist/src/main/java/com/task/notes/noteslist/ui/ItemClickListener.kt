package com.task.notes.noteslist.ui

interface ItemClickListener<T> {
    fun onItemClick(model: T)
}