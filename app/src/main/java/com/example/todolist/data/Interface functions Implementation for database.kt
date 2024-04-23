package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class InterfaceImplementation(private val dao: DataBaseOperations):
    DatabaseRepository  {
    override suspend fun insertTodo(toDo: ToDoEntry) {
        dao.insertTodo(toDo)
    }

    override suspend fun deleteToDo(toDo: ToDoEntry) {
       dao.deleteToDo(toDo)
    }

    override suspend fun selectToDo(id: Int): ToDoEntry? {
        return dao.selectToDo(id)
    }

    override fun selectToDos(id: Int): Flow<List<ToDoEntry>> {
        return dao.selectToDos(id)
    }
}