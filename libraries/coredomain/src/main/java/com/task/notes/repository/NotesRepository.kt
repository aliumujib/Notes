package com.task.notes.repository

import com.task.noteapp.models.Note
import kotlinx.coroutines.flow.Flow
import org.joda.time.DateTime

interface NotesRepository {

    fun streamNotes(): Flow<List<Note>>

    suspend fun saveNote(
        id: Int,
        title: String,
        note: String,
        imageURL: String?,
        timeStamp: Long = DateTime().millis
    )

    suspend fun fetchNote(id: Int): Note?

    fun deleteNote(id: Int)
}
