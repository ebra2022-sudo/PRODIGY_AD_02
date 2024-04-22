package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Todolist database entry
@Entity
data class ToDoEntry(
    var title: String,
    var description: String?,
    var isDone: Boolean,
    @PrimaryKey val id: Int?
)