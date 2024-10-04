package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun MainScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel = koinViewModel()
) {
    val tasksList = viewModel.getTasksList().collectAsState(initial = emptyList())
    var showDeleteDialog by remember { mutableStateOf(false) }
    var taskToDelete by remember { mutableStateOf<Task?>(null) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            items(
                items = tasksList.value,
                key = { it.uid },
                itemContent = { task ->
                    val taskState = remember { mutableStateOf(task.state) }
                    Row(
                        Modifier
                            .fillMaxWidth(0.95f)
                            .clickable {
                                val taskUid = task.uid
                                navController.navigate(
                                    buildString {
                                        append(ScreenRoutes.EditTaskScreen.route)
                                        append("/$taskUid")
                                    }
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = task.taskName,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        )
                        Switch(checked = taskState.value, onCheckedChange = {
                            taskState.value = it
                            task.state = it
                            viewModel.updateTask(task)
                        })
                        IconButton(onClick = {
                            taskToDelete = task
                            showDeleteDialog = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete_btn),
                                contentDescription = EMPTY_STRING,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        }
        IconButton(
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                navController.navigate(ScreenRoutes.AddTaskScreen.route)
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_btn),
                contentDescription = EMPTY_STRING,
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
    }
    if (showDeleteDialog && taskToDelete != null) {
        ConfirmDeleteDialog(
            onConfirm = {
                taskToDelete?.let { viewModel.deleteTask(it) }
                taskToDelete = null
                showDeleteDialog = false
            },
            onDismiss = {
                taskToDelete = null
                showDeleteDialog = false
            }
        )
    }
}

@Composable
fun ConfirmDeleteDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(id = R.string.confirm_delete)) },
        text = { Text(stringResource(id = R.string.confirm_delete_description)) },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(stringResource(id = R.string.btn_delete))
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text(stringResource(id = R.string.btn_cancel))
            }
        }
    )
}
