package com.programming.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ToDoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addATodo(todoEntity: Todo)

    // Loads all the todos from the to-do table
    @Query("Select * from `todo-table`")
    abstract fun getAllTodos(): Flow<List<Todo>>

    @Update
    abstract suspend fun updateATodo(todoEntity: Todo)

    @Delete
    abstract suspend fun deleteATodo(todoEntity: Todo)

    @Query("Select * from `todo-table` WHERE id = :id")
    abstract fun getATodoById(id:Long): Flow<Todo>

}