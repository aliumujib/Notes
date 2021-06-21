package com.task.notes.noteseditor.presentation

import androidx.lifecycle.ViewModel
import com.task.noteapp.models.Note
import com.task.noteapp.sharedlib.ext.getViewModelScope
import com.task.noteapp.sharedlib.ext.updateValue
import com.task.notes.noteseditor.domain.FetchNoteUseCase
import com.task.notes.noteseditor.domain.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
@ExperimentalCoroutinesApi
class SaveNoteViewModel @Inject constructor(
    private val scope: CoroutineScope?,
    private val fetchNote: FetchNoteUseCase,
    private val saveNote: SaveNoteUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.init())
    val viewState: StateFlow<ViewState> = _viewState

    private val _actions: MutableStateFlow<Action> = MutableStateFlow(Action.None)
    val actions: StateFlow<Action> = _actions

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
                    copy(loadState = LoadState.Error, error = e)
                }
            }

            _viewState.updateValue {
                copy(loadState = LoadState.Success, error = null)
            }
            _actions.updateValue {
                Action.GoBack
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
    }

    fun deleteNote() {
    }

    companion object {

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
