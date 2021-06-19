package com.task.notes.cache

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.base.Predicates.equalTo
import com.google.common.truth.Truth
import com.task.notes.cache.dao.NotesDao
import com.task.notes.cache.db.Database
import com.task.notes.cache.models.NotesEntity
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


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

    @Test
    fun writeNoteAndReadInList() = runBlockingTest {
        val notes: List<NotesEntity> = DaoTestUtils.dummyList
        notesDao.saveNotes(notes)
        val notesData: List<NotesEntity> = notesDao.getNotes()
        Truth.assertThat(notesData).isEqualTo(notes)
    }
}