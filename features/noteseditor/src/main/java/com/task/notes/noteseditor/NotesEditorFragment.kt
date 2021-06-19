package com.task.notes.noteseditor

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.task.notes.noteseditor.ext.viewBinding
import com.task.notesapp.noteseditor.R
import com.task.notesapp.noteseditor.databinding.FragmentNotesEditorBinding

class NotesEditorFragment : Fragment(R.layout.fragment_notes_editor) {

    private val binding by viewBinding(FragmentNotesEditorBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
