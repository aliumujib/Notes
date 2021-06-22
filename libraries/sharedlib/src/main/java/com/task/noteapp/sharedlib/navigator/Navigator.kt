package com.task.noteapp.sharedlib.navigator

import android.view.View

interface Navigator {

    fun goToNoteEditor(noteId: Int, sharedView: View?)

    fun goBack()
}
