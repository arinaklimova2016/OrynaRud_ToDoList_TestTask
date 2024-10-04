package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.KEY_TASK_UID
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.ui.screens.AddTaskScreen
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.ui.screens.EditTaskScreen
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.ui.screens.MainScreen

@Composable
fun TaskNavigation(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.MainScreen.route
    ) {
        composable(ScreenRoutes.MainScreen.route) {
            MainScreen(paddingValues, navController)
        }

        composable(ScreenRoutes.AddTaskScreen.route) {
            AddTaskScreen(paddingValues, navController)
        }

        composable(
            route = buildString {
                append(ScreenRoutes.EditTaskScreen.route)
                append("/{$KEY_TASK_UID}")
            },
            arguments = listOf(navArgument(KEY_TASK_UID) { type = NavType.IntType })
        ) {
            EditTaskScreen(
                taskUid = it.arguments?.getInt(KEY_TASK_UID) ?: 0,
                paddingValues = paddingValues,
                navController = navController
            )
        }
    }
}