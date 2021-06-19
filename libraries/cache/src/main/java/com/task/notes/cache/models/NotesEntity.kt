package com.task.notes.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    val title: String,
    val note: String,
    val imageURL: String?,
    val createdAt:Long,
    val editedAt: Long
)