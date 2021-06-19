package com.task.noteapp.data.repository.notes

import com.task.noteapp.data.repository.notes.mappers.noteEntityToNote
import com.task.noteapp.data.repository.notes.mappers.noteToNoteEntity
import com.task.noteapp.models.Note
import com.task.notes.cache.dao.NotesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * This could have been defined as an interface
 * but it'd have been pointless considering the fact that there is
 * no defined `Domain` layer to seperate the actual implemention from the UI
 * in this project structure.
 * */
class NotesRepository @Inject constructor(private val notesDao: NotesDao) {

    fun streamNotes(): Flow<List<Note>> {
        return notesDao.streamNotes().map {
            it.map { note ->
                noteEntityToNote(note)
            }
        }
    }

    suspend fun saveNote(note: Note) {
        return notesDao.saveNote(noteToNoteEntity(note))
    }

    suspend fun fetchNote(id: Int): Note {
        return noteEntityToNote(notesDao.getNoteWithId(id))
    }

}