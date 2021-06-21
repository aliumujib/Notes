package com.task.notes.noteslist.domain

import com.task.noteapp.models.Note
import com.task.notes.repository.NotesRepository
import com.task.notes.utils.FlowUseCase
import com.task.notes.utils.NoParamsException
import com.task.notes.utils.PostExecutionThread
import com.task.notes.utils.SuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StreamNotesUseCase @Inject constructor(
    private val repository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<Note>>(postExecutionThread) {


    override fun build(params: Unit?): Flow<List<Note>> {
        return repository.streamNotes()
    }


}
