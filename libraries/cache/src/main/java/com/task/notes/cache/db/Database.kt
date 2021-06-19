package com.task.notes.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.notes.cache.dao.NotesDao
import com.task.notes.cache.models.NoteEntity


@Database(
    entities = [
        NoteEntity::class,
    ],
    version = 1,
)
@TypeConverters()
abstract class Database : RoomDatabase() {

    abstract fun notesDao(): NotesDao

}
