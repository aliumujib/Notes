package com.task.notes.noteslist.domain

import com.task.noteapp.models.Note
import com.task.notes.repository.NotesRepository
import com.task.notes.utils.FlowUseCase
import com.task.notes.utils.PostExecutionThread
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class StreamNotesUseCase @Inject constructor(
    private val repository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<Note>>(postExecutionThread) {

    override fun build(params: Unit?): Flow<List<Note>> {
        return repository.streamNotes()
    }
}
