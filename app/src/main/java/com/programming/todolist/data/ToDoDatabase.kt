package com.programming.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)

abstract class ToDoDatabase: RoomDatabase() {
    abstract fun  todoDAO(): ToDoDAO
}