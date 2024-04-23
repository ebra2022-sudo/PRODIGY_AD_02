package com.example.todolist
import android.app.Application


@ToDoApplication.HiltAndroidApp
class ToDoApplication: Application() {
    annotation class HiltAndroidApp
}