package com.task.notes.noteseditor.domain

import com.task.noteapp.models.Note
import com.task.notes.repository.NotesRepository
import com.task.notes.utils.NoParamsException
import com.task.notes.utils.PostExecutionThread
import com.task.notes.utils.SuspendUseCase
import javax.inject.Inject

class FetchNoteUseCase @Inject constructor(
    private val repository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : SuspendUseCase<FetchNoteUseCase.Params, Note>(postExecutionThread) {

    data class Params constructor(val id: Int) {
        companion object {
            fun make(id: Int): Params {
                return Params(id)
            }
        }
    }

    override suspend fun execute(params: Params?): Note {
        if (params == null) {
            throw NoParamsException()
        }
        return repository.fetchNote(params.id)
    }
}
