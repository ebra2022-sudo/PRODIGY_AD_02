package com.example.todolist.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel(){
    private var _title by mutableStateOf("")
    val title: String
        get() = _title
    val onTitleChange = { new: String -> _title = new }

}