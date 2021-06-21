package com.task.notes.noteslist.presentation

import com.google.common.truth.Truth.assertThat
import com.task.notes.noteslist.domain.StreamNotesUseCase
import com.task.notes.noteslist.presentation.NotesListViewModel.Companion.LoadState.Error
import com.task.notes.noteslist.presentation.NotesListViewModel.Companion.LoadState.Loading
import com.task.notes.noteslist.presentation.NotesListViewModel.Companion.LoadState.Success
import com.task.notes.noteslist.presentation.NotesListViewModel.Companion.ViewState
import com.task.notes.testsharedutils.DomainTestUtils
import com.task.notes.utils.NoParamsException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class StreamNotesViewModelTest {

    @MockK
    private lateinit var streamNotesUseCase: StreamNotesUseCase

    private lateinit var streamNotesViewModel: NotesListViewModel

    private val testScope = TestCoroutineScope()
    private val notes = DomainTestUtils.dummyList

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        streamNotesViewModel =
            NotesListViewModel(testScope, streamNotesUseCase)
    }

    @Test
    fun test_streamNotesEmitsStatesCorrectlyWhenSuccessful() = runBlockingTest {
       val job = testScope.launch {
           coEvery { streamNotesUseCase.build() } returns flowOf(notes)
           val expected = listOf(ViewState(Loading, emptyList(), null), ViewState(Success, notes, null))
           val currentState = streamNotesViewModel.viewState.toList()
           assertThat(currentState).isEqualTo(expected)
       }
        job.cancel()
    }

    @Test
    fun test_streamNotesEmitsStatesCorrectlyWhenFailed() = runBlockingTest {
        val job = testScope.launch {
            coEvery { streamNotesUseCase.build() } throws NoParamsException()
            val expected = listOf(ViewState(Loading, emptyList(), null), ViewState(Error, emptyList(), NoParamsException()))
            val currentState = streamNotesViewModel.viewState.toList()
            assertThat(currentState).isEqualTo(expected)
        }
        job.cancel()
    }

}
