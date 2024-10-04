package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.navigation

import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.ADD_SCREEN
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.EDIT_SCREEN
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.MAIN_SCREEN

sealed class ScreenRoutes(val route: String) {
    data object MainScreen : ScreenRoutes(MAIN_SCREEN)
    data object AddTaskScreen : ScreenRoutes(ADD_SCREEN)
    data object EditTaskScreen : ScreenRoutes(EDIT_SCREEN)
}