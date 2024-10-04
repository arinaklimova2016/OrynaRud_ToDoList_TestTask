package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.DATE_FORMATTER_PATTERN
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.EMPTY_STRING
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.navigation.ScreenRoutes
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.view_models.MainViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun EditTaskScreen(
    taskUid: Int,
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel = koinViewModel()
) {
    val task by viewModel.taskByUid.observeAsState()
    val nameState = remember { mutableStateOf(task?.taskName) }
    val descriptionState = remember { mutableStateOf(task?.taskDescription) }
    val formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN)
    val errorState = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getTaskByUid(taskUid)
    }

    LaunchedEffect(task) {
        nameState.value = task?.taskName
        descriptionState.value = task?.taskDescription
    }

    BackHandler {
        task?.lastViewedAt = LocalDateTime.now()
        task?.let { viewModel.updateTask(it) }
        navController.popBackStack()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.95f)) {
            TextField(
                value = nameState.value ?: stringResource(id = R.string.smth_went_wrong),
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
                value = descriptionState.value ?: EMPTY_STRING,
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
        Column(Modifier.fillMaxWidth(0.95f), horizontalAlignment = Alignment.Start) {
            Text(
                text = buildString {
                    append(stringResource(id = R.string.created_at))
                    append(task?.createdAt?.format(formatter))
                }
            )
            Text(
                text = buildString {
                    append(stringResource(id = R.string.viewed_at))
                    append(task?.lastViewedAt?.format(formatter))
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                onClick = {
                    if (nameState.value != EMPTY_STRING) {
                        task?.taskName = nameState.value ?: EMPTY_STRING
                        task?.taskDescription = descriptionState.value ?: EMPTY_STRING
                        task?.lastViewedAt = LocalDateTime.now()
                        task?.let { viewModel.updateTask(it) }
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
}
