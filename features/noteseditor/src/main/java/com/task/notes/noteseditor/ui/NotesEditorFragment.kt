package com.task.notes.noteseditor.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.task.noteapp.sharedlib.ext.viewBinding
import com.task.notes.noteseditor.presentation.SaveNoteViewModel
import com.task.notesapp.noteseditor.R
import com.task.notesapp.noteseditor.databinding.FragmentNotesEditorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class NotesEditorFragment : Fragment(R.layout.fragment_notes_editor) {

    private val binding by viewBinding(FragmentNotesEditorBinding::bind)
    private val viewModel by viewModels<SaveNoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        listenForStateChanges()
        listenForActions()
    }

    private fun listenForActions() {
        viewModel.actionState.onEach {
            handleAction(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleAction(action: SaveNoteViewModel.Companion.Action) {
        when (action) {
            SaveNoteViewModel.Companion.Action.GoBack -> {
            }
            SaveNoteViewModel.Companion.Action.None -> {
            }
        }
    }

    private fun listenForStateChanges() {
        viewModel.viewState.onEach {
            handleViewState(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleViewState(viewState: SaveNoteViewModel.Companion.ViewState) {
        when (viewState.loadState) {
            SaveNoteViewModel.Companion.LoadState.Error -> {
            }
            SaveNoteViewModel.Companion.LoadState.Idle -> {
            }
            SaveNoteViewModel.Companion.LoadState.Loading -> {
            }
            SaveNoteViewModel.Companion.LoadState.Success -> {
            }
        }
    }

    private fun initToolbar() {
        binding.toolbar.inflateMenu(R.menu.create_fragment_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.done -> {
                }
                R.id.add_photo -> {
                }
                R.id.delete -> {
                }
            }
            true
        }
    }
}
