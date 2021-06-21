package com.task.notes.noteslist.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.noteapp.models.Note
import com.task.noteapp.sharedlib.ext.dpToPx
import com.task.noteapp.sharedlib.ext.viewBinding
import com.task.noteapp.sharedlib.navigator.Navigator
import com.task.notes.noteslist.R
import com.task.notes.noteslist.databinding.FragmentNotesListBinding
import com.task.notes.noteslist.presentation.NotesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.cabriole.decorator.LinearMarginDecoration
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class NotesListFragment : Fragment(R.layout.fragment_notes_list), ItemClickListener<Note> {

    private val viewModel by viewModels<NotesListViewModel>()
    private val binding: FragmentNotesListBinding by viewBinding(FragmentNotesListBinding::bind)

    @Inject
    lateinit var navigator: Navigator

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initRecyclerView()
        listenForStateChanges()
    }

    private fun initViews() {
        binding.addButton.setOnClickListener {
            navigator.goToNoteEditor(null)
        }
    }

    private fun initRecyclerView() {
        binding.notesList.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                LinearMarginDecoration(
                    leftMargin = resources.dpToPx(16),
                    topMargin = resources.dpToPx(8),
                    rightMargin = resources.dpToPx(16),
                    bottomMargin = resources.dpToPx(8),
                    orientation = LinearLayout.VERTICAL,
                    inverted = false,
                    decorationLookup = null
                )
            )
        }
    }

    private fun listenForStateChanges() {
        viewModel.viewState.onEach {
            handleViewState(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleViewState(state: NotesListViewModel.Companion.ViewState) {
        when (state.loadState) {
            NotesListViewModel.Companion.LoadState.Error -> {
                binding.notesList.isVisible = false
                binding.errorEmptyView.isVisible = true
            }
            NotesListViewModel.Companion.LoadState.Idle -> {
            }
            NotesListViewModel.Companion.LoadState.Loading -> {
            }
            NotesListViewModel.Companion.LoadState.Success -> {
                if (state.notes.isEmpty()) {
                    binding.errorEmptyView.isVisible = true
                    binding.notesList.isVisible = false
                } else {
                    binding.errorEmptyView.isVisible = false
                    binding.notesList.isVisible = true
                    notesAdapter.submitList(state.notes)
                }
            }
        }
    }

    override fun onItemClick(model: Note) {
        navigator.goToNoteEditor(model._id)
    }
}
