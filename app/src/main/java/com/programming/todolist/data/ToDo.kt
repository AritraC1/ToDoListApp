package com.programming.todolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="todo-table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name="todo-title")
    val title: String="",

    @ColumnInfo(name="todo-desc")
    val description:String=""
)

object DummyTodo{
    val TodoList = listOf(

        // Dummy data 1
        Todo(
            title="Google Watch 2",
            description =  "An android Watch"
        ),

        // Dummy data 2
        Todo(
            title = "Oculus Quest 2",
            description = "A VR headset for playing games"
        ),

        // Dummy data 3
        Todo(
            title = "A Sci-fi, Book",
            description= "A science friction book from any best seller"
        ),

        // Dummy data 4
        Todo(
            title = "Bean bag",
            description = "A comfy bean bag to substitute for a chair"
        )
    )
}