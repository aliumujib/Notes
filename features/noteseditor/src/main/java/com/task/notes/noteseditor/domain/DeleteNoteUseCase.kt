package com.task.notes.noteseditor.domain

import com.task.notes.repository.NotesRepository
import com.task.notes.utils.NoParamsException
import com.task.notes.utils.PostExecutionThread
import com.task.notes.utils.SuspendUseCase
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : SuspendUseCase<DeleteNoteUseCase.Params, Unit>(postExecutionThread) {

    data class Params constructor(val id: Int) {
        companion object {
            fun make(id: Int): Params {
                return Params(id)
            }
        }
    }

    override suspend fun execute(params: Params?) {
        if (params == null) {
            throw NoParamsException()
        }
        return repository.deleteNote(params.id)
    }
}
