package com.example.todolist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TodoRepository(private val databaseOperations: DataBaseOperations) {
    val searchResults = MutableLiveData<List<ToDoEntry>>()

    // to access the updated database records.
    val allTodos: LiveData<List<ToDoEntry>> = databaseOperations.getAllTodos()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun insertTodo(newToDoEntry: ToDoEntry) {
        coroutineScope.launch(Dispatchers.IO) {
            databaseOperations.insertTodo(newToDoEntry)
        }
    }

    fun deleteTodo(toDoEntry: ToDoEntry) {
        coroutineScope.launch(Dispatchers.IO) {
            databaseOperations.deleteToDo(toDoEntry)
        }
    }

    fun searchByName(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    fun searchById(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(id).await()
        }
    }

    // generic function for search async
    private fun <T> asyncFind(key: T): Deferred<List<ToDoEntry>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async when (key) {
                is String -> databaseOperations.selectToDoByTitle(key)
                else -> databaseOperations.selectToDoById(key as Int)
            }
        }
}
