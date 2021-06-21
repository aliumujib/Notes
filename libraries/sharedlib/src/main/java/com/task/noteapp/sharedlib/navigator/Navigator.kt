package com.task.noteapp.sharedlib.navigator

interface Navigator {

    fun goToNoteEditor(noteId: Int?)

    fun goBack()
}
