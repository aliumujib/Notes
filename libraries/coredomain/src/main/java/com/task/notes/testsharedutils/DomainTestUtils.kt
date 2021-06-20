package com.task.notes.testsharedutils

import com.task.noteapp.models.Note
import org.joda.time.DateTime

object DomainTestUtils {

    private val note1 =
        Note(
            1,
            "Title 1",
            "Content 1",
            "http://unsplash.com",
            DateTime(202903443)
        )

    private val note2 =
        Note(
            32,
            "Title 2",
            "Content 2",
            "http://unsplash.com",
            DateTime(2345678989)
        )

    private val note3 =
        Note(
            3,
            "Title 3",
            "Content 3",
            "http://unsplash.com",
            DateTime(43576732445)
        )

    val dummyList = listOf(note1, note2, note3)
}
