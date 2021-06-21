package com.task.notes.noteslist.domain

import com.task.notes.repository.NotesRepository
import com.task.notes.testsharedutils.TestPostExecutionThreadImpl
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
class StreamNotesUseCaseTest {

    @MockK
    lateinit var notesRepository: NotesRepository

    lateinit var sut: StreamNotesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        sut = StreamNotesUseCase(notesRepository, TestPostExecutionThreadImpl())
    }

    @After
    fun tearDown() {
    }
}
