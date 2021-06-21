package com.task.noteapp.data.repository.notes

import com.google.common.truth.Truth.assertThat
import com.task.noteapp.data.repository.notes.mappers.noteEntityToNote
import com.task.noteapp.data.repository.notes.mappers.noteToNoteEntity
import com.task.noteapp.models.Note
import com.task.notes.cache.models.NoteEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.joda.time.DateTime
import org.junit.Test

@ExperimentalCoroutinesApi
class MappersTest {

    private val noteEntity =
        NoteEntity(
            1,
            "Title 1",
            "Content 1",
            "http://unsplash.com",
            1624248633
        )

    private val note =
        Note(
            1,
            "Title 1",
            "Content 1",
            "http://unsplash.com",
            DateTime(1624248633)
        )

    @Test
    fun test_noteEntityToNoteDoesCorrectMapping() = runBlockingTest {
        val expected = note
        val actual = noteEntityToNote(noteEntity)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun test_saveNoteCorrectlyMapsDataAndCallsNotesDao() = runBlockingTest {
        val expected = noteEntity
        val actual = noteToNoteEntity(note)
        assertThat(actual).isEqualTo(expected)
    }
}
