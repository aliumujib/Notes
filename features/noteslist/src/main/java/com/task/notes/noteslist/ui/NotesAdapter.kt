package com.task.notes.noteslist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.task.noteapp.models.Note
import com.task.notes.noteslist.databinding.ItemNoteBinding
import org.joda.time.format.DateTimeFormat

class NotesAdapter(private val itemClickListener: ItemClickListener<Note>?) :
    ListAdapter<Note, NotesAdapter.NoteItemViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
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
        private val clickListener: ItemClickListener<Note>?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Note) {
            with(data) {
                binding.noteImage.isVisible = !imageURL.isNullOrEmpty()
                imageURL?.let {
                    binding.noteImage.load(it)
                }
                binding.noteTitle.text = title
                binding.noteContent.text = note
                binding.noteLastEdit.text = DateTimeFormat.forPattern("dd/MM/yyyy").print(lastEdit)
                binding.root.children.forEach { child ->
                    child.setOnClickListener {
                        clickListener?.onItemClick(data)
                    }
                }
            }
        }
    }
}
