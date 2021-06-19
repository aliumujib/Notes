package com.task.notes.cache

import com.task.notes.cache.models.NotesEntity

object DaoTestUtils {

    private val notesEntity1 =
        NotesEntity(
            1,
            "Title 1",
            "Content 1",
            "http://unsplash.com",
            120202020,
            202903443
        )

    private val notesEntity2 =
        NotesEntity(
            32,
            "Title 2",
            "Content 2",
            "http://unsplash.com",
            1213445667,
            2345678989
        )

    private val notesEntity3 =
        NotesEntity(
            3,
            "Title 3",
            "Content 3",
            "http://unsplash.com",
            2345677889,
            43576732445
        )

    val dummyList = listOf(notesEntity1, notesEntity2, notesEntity3)
}