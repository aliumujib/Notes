package com.task.noteapp.data.repository.notes

import com.task.noteapp.models.Constants
import com.task.noteapp.models.Note
import com.task.notes.cache.models.NoteEntity
import org.joda.time.LocalDate

fun noteEntityToNote(noteEntity: NoteEntity): Note {
    return with(noteEntity) {
        Note(
            _id,
            title,
            note,
            imageURL ?: Constants.NOT_AVAILABLE,
            LocalDate(createdAt),
            LocalDate(editedAt)
        )
    }
}

fun noteToNoteEntity(noteModel: Note): NoteEntity {
    return with(noteModel) {
        NoteEntity(_id, title, note, imageURL, createdAt.toDate().time, editedAt.toDate().time)
    }
}
