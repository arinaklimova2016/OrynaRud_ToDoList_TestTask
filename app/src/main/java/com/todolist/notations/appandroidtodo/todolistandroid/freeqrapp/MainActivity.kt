package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.navigation.TaskNavigation
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.ui.theme.OrynaRud_ToDoList_TestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrynaRud_ToDoList_TestTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskNavigation(innerPadding)
                }
            }
        }
    }
}