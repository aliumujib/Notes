package com.task.notes.noteslist.ui

import android.view.View

interface ItemClickListener<T> {
    fun onItemClick(model: T, itemView: View)
}
