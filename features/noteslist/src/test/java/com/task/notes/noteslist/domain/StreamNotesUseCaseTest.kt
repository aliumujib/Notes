package com.task.notes.noteslist.domain

import com.google.common.truth.Truth
import com.task.notes.repository.NotesRepository
import com.task.notes.testsharedutils.DomainTestUtils
import com.task.notes.testsharedutils.TestPostExecutionThreadImpl
import com.task.notes.utils.PostExecutionThread
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class StreamNotesUseCaseTest {

    @MockK
    lateinit var notesRepository: NotesRepository

    private lateinit var sut: StreamNotesUseCase
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()
    private val notes = DomainTestUtils.dummyList

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = StreamNotesUseCase(notesRepository, postExecutionThread)
    }

    @Test
    fun test_streamNoteReturnsCorrectData() = runBlockingTest {
        every { notesRepository.streamNotes() } returns flowOf(notes)
        val actual = sut.build().first()
        coVerify(exactly = 1) {
            notesRepository.streamNotes()
        }
        Truth.assertThat(actual).isEqualTo(notes)
    }

    @After
    fun tearDown() {
    }
}
