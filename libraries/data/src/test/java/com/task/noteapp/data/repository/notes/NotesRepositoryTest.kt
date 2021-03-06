package com.task.noteapp.data.repository.notes

import com.google.common.truth.Truth.assertThat
import com.task.noteapp.data.repository.notes.mappers.noteEntityToNote
import com.task.noteapp.data.repository.notes.mappers.noteToNoteEntity
import com.task.notes.cache.dao.NotesDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NotesRepositoryTest {

    @MockK
    lateinit var notesDao: NotesDao

    private lateinit var repository: NotesRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        repository = NotesRepositoryImpl(notesDao)
    }

    @Test
    fun test_streamNotesCorrectlyCallsNotesDao() = runBlockingTest {
        every { notesDao.streamNotes() } returns flowOf(RepoTestUtils.dummyDaoList)
        val actual = repository.streamNotes().first()
        val expected = RepoTestUtils.dummyList
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun test_saveNoteCorrectlyMapsDataAndCallsNotesDaoWhenThereIsAnExistingNote() = runBlockingTest {
        val note = RepoTestUtils.dummyList[0]
        coEvery { notesDao.getNoteWithId(note._id) } returns RepoTestUtils.dummyDaoList.first { note._id == it._id }
        val noteEntity = noteToNoteEntity(note)
        repository.saveNote(note._id, note.title, note.note, note.imageURL, note.lastEdit.millis)
        coVerify(exactly = 1) {
            notesDao.saveNote(noteEntity.copy(edited = true))
        }
    }

    @Test
    fun test_saveNoteCorrectlyMapsDataAndCallsNotesDaoWhenThereIsNoExistingNote() = runBlockingTest {
        val note = RepoTestUtils.dummyList[0]
        coEvery { notesDao.getNoteWithId(note._id) } returns null
        val noteEntity = noteToNoteEntity(note)
        repository.saveNote(note._id, note.title, note.note, note.imageURL, note.lastEdit.millis)
        coVerify(exactly = 1) {
            notesDao.saveNote(noteEntity)
        }
    }

    @Test
    fun test_fetchNoteCorrectlyCallsNotesDao() = runBlockingTest {
        val noteEntity = RepoTestUtils.dummyDaoList[0]
        coEvery { notesDao.getNoteWithId(noteEntity._id) } returns RepoTestUtils.dummyDaoList.first { noteEntity._id == it._id }
        val expected = noteEntityToNote(noteEntity)
        val actual = repository.fetchNote(noteEntity._id)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun test_deleteNoteCorrectlyCallsNotesDao() = runBlockingTest {
        val noteEntity = RepoTestUtils.dummyDaoList[0]
        repository.deleteNote(noteEntity._id)
        coVerify(exactly = 1) {
            notesDao.deleteNoteWithId(noteEntity._id)
        }
    }
}
