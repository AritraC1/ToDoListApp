package com.programming.todolist

import android.content.Context
import androidx.room.Room
import com.programming.todolist.data.ToDoDatabase
import com.programming.todolist.data.ToDoRepo


object Graph {
    lateinit var database: ToDoDatabase

    val ToDoRepo by lazy {
        ToDoRepo(todoDao = database.todoDAO())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, ToDoDatabase::class.java, "todolist.db").build()
    }
}