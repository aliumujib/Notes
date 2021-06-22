package com.task.noteapp.data.repository.notes

import com.task.noteapp.data.repository.notes.mappers.noteEntityToNote
import com.task.noteapp.models.Note
import com.task.notes.cache.dao.NotesDao
import com.task.notes.cache.models.NoteEntity
import com.task.notes.repository.NotesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl @Inject constructor(private val notesDao: NotesDao) : NotesRepository {

    override fun streamNotes(): Flow<List<Note>> {
        return notesDao.streamNotes().map {
            it.map { note ->
                noteEntityToNote(note)
            }
        }
    }

    override suspend fun saveNote(
        id: Int,
        title: String,
        note: String,
        imageURL: String?,
        timeStamp: Long
    ) {
        val currentVersion: NoteEntity? = notesDao.getNoteWithId(id)
        notesDao.saveNote(NoteEntity(id, title, note, imageURL, timeStamp, currentVersion != null))
    }

    override suspend fun fetchNote(id: Int): Note? {
        val entity = notesDao.getNoteWithId(id)
        return entity?.let {
            noteEntityToNote(it)
        }
    }

    override fun deleteNote(id: Int) {
        notesDao.deleteNoteWithId(id)
    }
}
