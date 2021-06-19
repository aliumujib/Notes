package com.task.notes.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.notes.cache.models.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveNote(vararg accounts: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveNotes(accounts: List<NoteEntity>)

    @Query("SELECT * FROM notes")
    abstract fun streamNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes")
    abstract fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes where _id == :id")
    abstract suspend fun getNoteWithId(id: Int): NoteEntity

    @Query("DELETE FROM notes")
    abstract suspend fun deleteNotes()

    @Query("DELETE FROM notes where _id == :id")
    abstract fun deleteNoteWithId(id: Int)

}
