package com.task.notes.noteseditor.domain

import com.task.notes.repository.NotesRepository
import com.task.notes.testsharedutils.DomainTestUtils
import com.task.notes.testsharedutils.TestPostExecutionThreadImpl
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
class DeleteNoteUseCaseTest {

    @MockK
    lateinit var notesRepository: NotesRepository
    lateinit var sut: DeleteNoteUseCase
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    private val note = DomainTestUtils.dummyList[0]

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = DeleteNoteUseCase(notesRepository, postExecutionThread)
    }

    @Test
    fun test_fetchNotesReturnsCorrectData() = runBlockingTest {
        coEvery { notesRepository.fetchNote(note._id) } returns note
        sut(DeleteNoteUseCase.Params.make(note._id))
        coVerify {
            notesRepository.deleteNote(note._id)
        }
    }
}
