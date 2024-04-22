package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


// Data Access Objects are the main classes where you define your database interactions(Interfaces).
// They can include a variety of query methods(such as insert, delete, searching, updating entry value, ..).
// Data access objects are implemented only using interface od abstract data class
// in this interface or abstract class you cannot implement the function, rather define the signature.
@Dao
interface DataBaseOperations {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // to update the todo if it exists already
    suspend fun insertTodo(toDo: ToDoEntry)

    @Delete
    suspend fun deleteToDo(toDo: ToDoEntry)

    @Query("SELECT * FROM todoentry WHERE id = id")
    suspend fun selectToDo(id: Int): ToDoEntry?

    @Query("SELECT * FROM todoentry")
    fun selectToDos(id: Int): Flow<List<ToDoEntry>>
}