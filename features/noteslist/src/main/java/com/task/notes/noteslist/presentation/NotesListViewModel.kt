package com.task.notes.noteslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.models.Note
import com.task.noteapp.sharedlib.ext.updateValue
import com.task.notes.noteslist.domain.StreamNotesUseCase
import com.task.notes.noteslist.presentation.NotesListViewModel.Companion.LoadState.Loading
import com.task.notes.noteslist.presentation.NotesListViewModel.Companion.LoadState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val streamNotes: StreamNotesUseCase
) :
    ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.init())
    val viewState: StateFlow<ViewState> = _viewState

    init {
        streamNotes()
    }

    private fun streamNotes() {
        streamNotes.build().onStart {
            _viewState.updateValue {
                copy(loadState = Loading)
            }
        }.onEach {
            _viewState.updateValue {
                copy(loadState = Success, notes = it)
            }
        }.catch {
            _viewState.updateValue {
                copy(loadState = LoadState.Error, error = it)
            }
        }.launchIn(viewModelScope)
    }


    companion object {

        data class ViewState(
            val loadState: LoadState,
            val notes: List<Note>,
            val error: Throwable?,
        ) {
            companion object {
                fun init(): ViewState {
                    return ViewState(LoadState.Idle, emptyList(), null)
                }
            }
        }

        sealed class LoadState {
            object Loading : LoadState()
            object Idle : LoadState()
            object Error : LoadState()
            object Success : LoadState()
        }

    }
}