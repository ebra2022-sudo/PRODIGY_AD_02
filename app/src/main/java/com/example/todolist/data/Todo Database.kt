package com.example.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.app.Application

@Database(
    entities = [ToDoEntry:: class],
    version = 1
)

// abstract class for data base
abstract class TodoDatabase: RoomDatabase() {
    // database operation
    abstract val dataBaseOperations: DataBaseOperations

}
