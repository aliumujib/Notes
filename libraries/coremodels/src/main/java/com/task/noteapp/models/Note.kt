package com.task.noteapp.models

import org.joda.time.DateTime

data class Note(
    val _id: Int,
    val title: String,
    val note: String,
    val imageURL: String,
    val createdAt: DateTime,
    val editedAt: DateTime
)
