package com.task.notes.noteseditor.presentation

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.task.notes.noteseditor.domain.FetchNoteUseCase
import com.task.notes.noteseditor.domain.SaveNoteUseCase
import com.task.notes.noteseditor.presentation.SaveNoteViewModel.Companion.Action
import com.task.notes.noteseditor.presentation.SaveNoteViewModel.Companion.LoadState.Success
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveNoteViewModelTest {

    @MockK
    private lateinit var fetchNoteUseCase: FetchNoteUseCase

    @MockK
    private lateinit var saveNoteUseCase: SaveNoteUseCase

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var saveNoteViewModel: SaveNoteViewModel

    private val testScope = TestCoroutineScope()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        every { savedStateHandle.get<Int>(any()) } returns 0
        coEvery { fetchNoteUseCase(any()) } returns null
        saveNoteViewModel =
            SaveNoteViewModel(testScope, savedStateHandle, fetchNoteUseCase, saveNoteUseCase)
    }

    @Test
    fun test_setNoteIdCorrectlyAppendsDataAndEmitsNewState() {
        val currentState = saveNoteViewModel.viewState.value
        val noteId = 2
        saveNoteViewModel.setNoteId(noteId)
        val newState = saveNoteViewModel.viewState.value
        assertThat(currentState.copy(noteId = noteId)).isEqualTo(newState)
    }

    @Test
    fun test_setImageURlCorrectlyAppendsDataAndEmitsNewState() {
        val currentState = saveNoteViewModel.viewState.value
        val imageURL = "http://unsplash.com/image"
        saveNoteViewModel.setImageURl(imageURL)
        val newState = saveNoteViewModel.viewState.value
        assertThat(currentState.copy(imageURL = imageURL)).isEqualTo(newState)
    }

    @Test
    fun test_saveNoteCorrectlyCallsSaveNoteUsecaseAndAppendsRelevantParams() {
        val currentState = saveNoteViewModel.viewState.value
        val imageURL = "http://unsplash.com/image"
        val title = "title1"
        val content = "desc1"
        saveNoteViewModel.setImageURl(imageURL)
        saveNoteViewModel.saveNote(title, content)
        val newState = saveNoteViewModel.viewState.value
        coVerify(exactly = 1) {
            val params = SaveNoteUseCase.Params.make(
                id = 0,
                title = title,
                note = content,
                imageURL = imageURL
            )
            saveNoteUseCase(params)
        }
        val action = saveNoteViewModel.actions.value
        assertThat(currentState.copy(imageURL = imageURL, loadState = Success)).isEqualTo(newState)
        assertThat(action).isEqualTo(Action.GoBack)
    }

    @Test
    fun deleteNote() {
    }
}
