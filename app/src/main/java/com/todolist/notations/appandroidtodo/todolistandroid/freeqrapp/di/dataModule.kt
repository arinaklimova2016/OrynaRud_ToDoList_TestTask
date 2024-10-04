package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.di

import androidx.room.Room
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.DATABASE_NAME
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.data.TaskDao
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.data.TaskDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory {
        Room.databaseBuilder(
            androidContext(),
            TaskDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    factory {
        provideTaskDao(get())
    }
}

private fun provideTaskDao(database: TaskDatabase): TaskDao {
    return database.taskDao()
}