package com.task.notes.noteseditor.domain

import com.google.common.truth.Truth
import com.task.noteapp.models.Note
import com.task.notes.repository.NotesRepository
import com.task.notes.testsharedutils.DomainTestUtils
import com.task.notes.testsharedutils.TestPostExecutionThreadImpl
import com.task.notes.utils.NoParamsException
import com.task.notes.utils.PostExecutionThread
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchNoteUseCaseTest {

    @MockK
    lateinit var notesRepository: NotesRepository
    lateinit var sut: FetchNoteUseCase
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    private val note = DomainTestUtils.dummyList[0]

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        sut = FetchNoteUseCase(notesRepository, postExecutionThread)
    }

    @Test
    fun test_fetchNotesReturnsCorrectData() = runBlockingTest {
        coEvery { notesRepository.fetchNote(note._id) } returns note
        val actual: Note = sut(FetchNoteUseCase.Params.make(note._id))
        val expected = note
        coVerify {
            notesRepository.fetchNote(note._id)
        }
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = NoParamsException::class)
    fun test_fetchNotesThrowsExceptionOnNullParams() = runBlockingTest {
        sut()
        coVerify(exactly = 0) {
            notesRepository.fetchNote(note._id)
        }
    }
}
