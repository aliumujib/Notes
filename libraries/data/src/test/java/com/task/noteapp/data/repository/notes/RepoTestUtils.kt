package com.task.noteapp.data.repository.notes

import com.task.noteapp.data.repository.notes.mappers.noteEntityToNote
import com.task.notes.cache.models.NoteEntity

object RepoTestUtils {

    private val notesEntity1 =
        NoteEntity(
            1,
            "Title 1",
            "Content 1",
            "http://unsplash.com",
            202903443
        )

    private val notesEntity2 =
        NoteEntity(
            32,
            "Title 2",
            "Content 2",
            "http://unsplash.com",
            2345678989
        )

    private val notesEntity3 =
        NoteEntity(
            3,
            "Title 3",
            "Content 3",
            "http://unsplash.com",
            43576732445
        )

    val dummyDaoList = listOf(notesEntity1, notesEntity2, notesEntity3)

    val dummyList = dummyDaoList.map {
        noteEntityToNote(it)
    }
}
