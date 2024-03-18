package com.programming.todolist.data

import kotlinx.coroutines.flow.Flow

class ToDoRepo(private val todoDao: ToDoDAO) {

    suspend fun addATodo(todo: Todo) {
        todoDao.addATodo(todo)
    }

    fun getTodos(): Flow<List<Todo>> = todoDao.getAllTodos()

    fun getATodoById(id:Long) : Flow<Todo> {
        return todoDao.getATodoById(id)
    }

    suspend fun updateATodo(todo: Todo){
        todoDao.updateATodo(todo)
    }

    suspend fun deleteATodo(todo: Todo){
        todoDao.deleteATodo(todo)
    }
}