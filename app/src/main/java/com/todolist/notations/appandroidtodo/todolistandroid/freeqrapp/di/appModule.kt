package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.di

import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.view_models.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(get())
    }
}
