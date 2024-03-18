package com.programming.todolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.programming.todolist.data.Todo
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: ToDoViewModel,
    navController: NavController
){

    val snackMessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0L){
        val wish = viewModel.getATodoById(id).collectAsState(initial = Todo(0L, "", ""))
        viewModel.TodoTitleState = wish.value.title
        viewModel.TodoDescriptionState = wish.value.description
    }else{
        viewModel.TodoTitleState = ""
        viewModel.TodoDescriptionState = ""
    }

    Scaffold(
        topBar = {AppBarView(title =
    if(id != 0L) stringResource(id = R.string.update_todo)
    else stringResource(id = R.string.add_todo)
    ) {navController.navigateUp()}
    },
        scaffoldState = scaffoldState
        ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            TodoTextField(label = "Title",
                value = viewModel.TodoTitleState,
                onValueChanged = {
                    viewModel.onTodoTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            TodoTextField(label = "Description",
                value = viewModel.TodoDescriptionState,
                onValueChanged = {
                    viewModel.onTodoDescriptionChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick={
                if(viewModel.TodoTitleState.isNotEmpty() &&
                    viewModel.TodoDescriptionState.isNotEmpty()){
                    if(id != 0L){
                        viewModel.updateToDo(
                            Todo(
                                id = id,
                                title = viewModel.TodoTitleState.trim(),
                                description = viewModel.TodoDescriptionState.trim()
                            )
                        )
                    }else{
                        //  AddWish
                        viewModel.addToDo(
                            Todo(
                                title = viewModel.TodoTitleState.trim(),
                                description = viewModel.TodoDescriptionState.trim())
                        )
                        snackMessage.value = "Task has been created"
                    }
                }else{
                    //
                    snackMessage.value = "Enter fields to create a wish"
                }
                scope.launch {
                    //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            }){
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_todo)
                    else stringResource(
                        id = R.string.add_todo
                    ),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }

}


@Composable
fun TodoTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.Black,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )


    )
}

@Preview
@Composable
fun TodoTestFieldPrev(){
    TodoTextField(label = "text", value = "text", onValueChanged = {})
}

