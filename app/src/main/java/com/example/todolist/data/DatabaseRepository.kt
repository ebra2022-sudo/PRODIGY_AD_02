package com.example.todolist.data

import kotlinx.coroutines.flow.Flow


// implementations for the data base operations
interface  DatabaseRepository {
    suspend fun insertTodo(toDo: ToDoEntry)
    suspend fun deleteToDo(toDo: ToDoEntry)
    suspend fun selectToDo(id: Int): ToDoEntry?
    fun selectToDos(id: Int): Flow<List<ToDoEntry>>
}