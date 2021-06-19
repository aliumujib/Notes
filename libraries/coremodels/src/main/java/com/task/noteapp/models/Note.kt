package com.task.noteapp.models

import org.joda.time.LocalDate

data class Note(
    val _id: Int,
    val title: String,
    val note: String,
    val imageURL: String,
    val createdAt: LocalDate,
    val editedAt: LocalDate
)