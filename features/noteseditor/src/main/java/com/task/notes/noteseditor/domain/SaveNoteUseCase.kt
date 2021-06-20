package com.task.notes.noteseditor.domain

import com.task.noteapp.models.Constants
import com.task.noteapp.models.Note
import com.task.notes.utils.NoParamsException
import com.task.notes.utils.PostExecutionThread
import com.task.notes.utils.SuspendUseCase
import com.task.notes.repository.NotesRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val repository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : SuspendUseCase<SaveNoteUseCase.Params, Unit>(postExecutionThread) {

    data class Params constructor(
        val id: Int,
        val title: String,
        val note: String,
        val imageURL: String?
    ) {
        companion object {
            fun make(
                id: Int = Constants.INVALID_ID,
                title: String,
                note: String,
                imageURL: String?
            ): Params {
                return Params(id, title, note, imageURL)
            }
        }
    }

    override suspend fun execute(params: Params?) {
        if (params == null) {
            throw NoParamsException()
        }
        return repository.saveNote(params.id, params.title, params.note, params.imageURL)
    }
}