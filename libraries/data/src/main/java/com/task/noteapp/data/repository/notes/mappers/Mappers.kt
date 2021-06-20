package com.task.noteapp.data.repository.notes.mappers

import com.task.noteapp.models.Constants
import com.task.noteapp.models.Note
import com.task.notes.cache.models.NoteEntity
import org.joda.time.DateTime

fun noteEntityToNote(noteEntity: NoteEntity): Note {
    return with(noteEntity) {
        Note(
            _id,
            title,
            note,
            imageURL ?: Constants.NOT_AVAILABLE,
            DateTime(editedAt)
        )
    }
}

fun noteToNoteEntity(noteModel: Note): NoteEntity {
    return with(noteModel) {
        NoteEntity(_id, title, note, imageURL, lastEdit.millis)
    }
}
