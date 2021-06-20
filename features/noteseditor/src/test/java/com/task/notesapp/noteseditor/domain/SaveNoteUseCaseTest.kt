package com.task.notesapp.noteseditor.domain

import com.task.notes.noteseditor.domain.SaveNoteUseCase
import com.task.notes.repository.NotesRepository
import com.task.notes.testsharedutils.DomainTestUtils
import com.task.notes.testsharedutils.TestPostExecutionThreadImpl
import com.task.notes.utils.NoParamsException
import com.task.notes.utils.PostExecutionThread
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveNoteUseCaseTest {

    @MockK
    lateinit var notesRepository: NotesRepository
    lateinit var sut: SaveNoteUseCase
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    private val note = DomainTestUtils.dummyList[0]

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        sut = SaveNoteUseCase(notesRepository, postExecutionThread)
    }

    @Test
    fun test_saveNoteSavesCorrectData() = runBlockingTest {
        sut(SaveNoteUseCase.Params.make(note._id, note.title, note.note, note.imageURL))
        coVerify(exactly = 1) {
            notesRepository.saveNote(note._id, note.title, note.note, note.imageURL)
        }
    }

    @Test(expected = NoParamsException::class)
    fun test_saveNotesThrowsExceptionOnNullParams() = runBlockingTest {
        sut()
        coVerify(exactly = 0) {
            notesRepository.saveNote(any(), any(), any(), any())
        }
    }
}
