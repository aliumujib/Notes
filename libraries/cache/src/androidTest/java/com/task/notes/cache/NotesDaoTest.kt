package com.task.notes.cache

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.task.notes.cache.dao.NotesDao
import com.task.notes.cache.db.Database
import com.task.notes.cache.models.NoteEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NotesDaoTest {
    private lateinit var notesDao: NotesDao
    private lateinit var db: Database

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()
        notesDao = db.notesDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun writeNoteAndReadInList() = runBlockingTest {
        val notes: List<NoteEntity> = DaoTestUtils.dummyList
        notesDao.saveNotes(notes)
        val noteData: List<NoteEntity> = notesDao.getNotes()
        Truth.assertThat(noteData).isEqualTo(notes)
    }
}