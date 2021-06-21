package com.task.notes.noteslist.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.task.noteapp.sharedlib.ext.viewBinding
import com.task.notes.noteslist.R
import com.task.notes.noteslist.databinding.FragmentNotesListBinding

class NotesListFragment : Fragment(R.layout.fragment_notes_list) {

    private val binding: FragmentNotesListBinding by viewBinding(FragmentNotesListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}