package com.task.notes.noteslist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.size.Scale
import com.task.noteapp.models.Note
import com.task.notes.noteslist.databinding.ItemNoteBinding

class NotesAdapter(private val itemClickListener: ItemClickListener<Note>?) :
    ListAdapter<Note, NotesAdapter.NoteItemViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note,
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note,
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val binding =
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteItemViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteItemViewHolder(
        private val binding: ItemNoteBinding,
        private val clickListener: ItemClickListener<Note>?,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {

        }
    }
}