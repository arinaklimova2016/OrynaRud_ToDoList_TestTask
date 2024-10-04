package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.R
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.EMPTY_STRING
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.data.Task
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.navigation.ScreenRoutes
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.view_models.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddTaskScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel = koinViewModel()
) {
    val nameState = remember { mutableStateOf(EMPTY_STRING) }
    val descriptionState = remember { mutableStateOf(EMPTY_STRING) }
    val errorState = remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.95f)) {
            TextField(
                value = nameState.value,
                onValueChange = { nameState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textStyle = TextStyle(fontSize = 18.sp),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.tf_enter_name),
                        color = if (errorState.value) Color.Red else Color.Black
                    )
                }
            )
            TextField(
                value = descriptionState.value,
                onValueChange = { descriptionState.value = it },
                textStyle = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                placeholder = {
                    Text(text = stringResource(id = R.string.tf_enter_description))
                }
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 16.dp),
            onClick = {
                if (nameState.value != EMPTY_STRING) {
                    viewModel.addTask(
                        Task(
                            state = false,
                            taskName = nameState.value,
                            taskDescription = descriptionState.value
                        )
                    )
                    navController.navigate(ScreenRoutes.MainScreen.route)
                } else {
                    errorState.value = true
                }
            }
        ) {
            Text(text = stringResource(id = R.string.btn_save))
        }
    }
}