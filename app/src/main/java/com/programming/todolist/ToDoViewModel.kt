package com.programming.todolist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programming.todolist.data.ToDoRepo
import com.programming.todolist.data.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ToDoViewModel (
    private val ToDoRepo: ToDoRepo = Graph.ToDoRepo
): ViewModel(){

    var TodoTitleState by mutableStateOf("")
    var TodoDescriptionState by mutableStateOf("")


    fun onTodoTitleChanged(newString:String){
        TodoTitleState = newString
    }

    fun onTodoDescriptionChanged(newString: String){
        TodoDescriptionState = newString
    }

    lateinit var getAllTodo: Flow<List<Todo>>

    init {
        viewModelScope.launch {
            getAllTodo = ToDoRepo.getTodos()
        }
    }

    fun addToDo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            ToDoRepo.addATodo(todo = todo)
        }
    }

    fun getATodoById(id:Long): Flow<Todo> {
        return ToDoRepo.getATodoById(id)
    }

    fun updateToDo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            ToDoRepo.updateATodo(todo = todo)
        }
    }

    fun deleteToDo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            ToDoRepo.deleteATodo(todo = todo)
            getAllTodo = ToDoRepo.getTodos()
        }
    }

}