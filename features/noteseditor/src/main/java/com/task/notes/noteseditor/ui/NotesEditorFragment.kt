package com.task.notes.noteseditor.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.task.noteapp.sharedlib.ext.viewBinding
import com.task.notes.noteseditor.contracts.PickImageContract
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

    private val binding: FragmentNotesEditorBinding by viewBinding(FragmentNotesEditorBinding::bind)
    private val viewModel by viewModels<SaveNoteViewModel>()

    private val pickImageCall =
        registerForActivityResult(PickImageContract()) { result ->
            result?.urls?.thumb?.let {
                viewModel.setImageURl(it)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        listenForStateChanges()
        listenForActions()
    }

    private fun listenForActions() {
        viewModel.actions.onEach {
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

        viewState.imageURL?.let {
            binding.noteImage.isVisible = true
            binding.noteImage.load(it)
            binding.guideline.setGuidelinePercent(0.2f)
        }
    }

    private fun initToolbar() {
        binding.toolbar.inflateMenu(R.menu.create_fragment_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.done -> {
                    viewModel.saveNote(binding.titleField.text.toString(), binding.contentField.text.toString())
                }
                R.id.add_photo -> {
                    pickImageCall.launch(false)
                }
                R.id.delete -> {
                    viewModel.deleteNote()
                }
            }
            true
        }
    }
}
