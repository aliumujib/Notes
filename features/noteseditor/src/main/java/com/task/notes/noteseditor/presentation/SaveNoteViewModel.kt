package com.task.notes.noteseditor.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.task.noteapp.models.Note
import com.task.noteapp.sharedlib.ext.getViewModelScope
import com.task.noteapp.sharedlib.ext.updateValue
import com.task.notes.noteseditor.domain.DeleteNoteUseCase
import com.task.notes.noteseditor.domain.FetchNoteUseCase
import com.task.notes.noteseditor.domain.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
@ExperimentalCoroutinesApi
class SaveNoteViewModel @Inject constructor(
    private val scope: CoroutineScope?,
    savedStateHandle: SavedStateHandle,
    private val fetchNote: FetchNoteUseCase,
    private val saveNote: SaveNoteUseCase,
    private val deleteNote: DeleteNoteUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.init())
    val viewState: StateFlow<ViewState> = _viewState

    private val _actions: MutableStateFlow<Action> = MutableStateFlow(Action.None)
    val actions: StateFlow<Action> = _actions

    init {
        savedStateHandle.get<Int>(ARG_NOTE_ID)?.let {
            setNoteId(it)
            fetchLastVersionData(it)
        }
    }

    private fun fetchLastVersionData(id: Int) {
        getViewModelScope(scope).launch {
            try {
                val note = fetchNote(FetchNoteUseCase.Params.make(id))
                _viewState.updateValue {
                    copy(loadState = LoadState.Success, error = null, lastVersion = note, imageURL = note?.imageURL)
                }
            } catch (e: Exception) {
                _viewState.updateValue {
                    copy(loadState = LoadState.Error, error = e)
                }
            }
        }
    }

    fun setNoteId(id: Int) {
        _viewState.updateValue {
            copy(noteId = id)
        }
    }

    fun setImageURl(url: String) {
        _viewState.updateValue {
            copy(imageURL = url)
        }
    }

    fun saveNote(title: String, note: String) {
        getViewModelScope(scope).launch {
            try {
                attemptSave(title, note)
            } catch (e: Exception) {
                _viewState.updateValue {
                    e.printStackTrace()
                    copy(loadState = LoadState.Error, error = e)
                }
            }
        }
    }

    private suspend fun attemptSave(
        title: String,
        note: String
    ) {
        _viewState.updateValue {
            copy(loadState = LoadState.Loading, error = null)
        }
        saveNote(
            SaveNoteUseCase.Params.make(
                viewState.value.noteId,
                title,
                note,
                viewState.value.imageURL
            )
        )
        _viewState.updateValue {
            copy(loadState = LoadState.Success, error = null)
        }
        _actions.updateValue {
            Action.GoBack
        }
    }

    fun deleteNote() {
        getViewModelScope(scope).launch {
            deleteNote.invoke(DeleteNoteUseCase.Params.make(viewState.value.noteId))
            _actions.updateValue {
                Action.GoBack
            }
        }
    }

    companion object {
        private const val ARG_NOTE_ID = "noteId"

        data class ViewState(
            val loadState: LoadState,
            val lastVersion: Note?,
            val noteId: Int,
            val imageURL: String?,
            val error: Throwable?
        ) {
            companion object {
                fun init(): ViewState {
                    return ViewState(LoadState.Idle, null, 0, null, null)
                }
            }
        }

        sealed class LoadState {
            object Loading : LoadState()
            object Idle : LoadState()
            object Error : LoadState()
            object Success : LoadState()
        }

        sealed class Action {
            object None : Action()
            object GoBack : Action()
        }
    }
}
