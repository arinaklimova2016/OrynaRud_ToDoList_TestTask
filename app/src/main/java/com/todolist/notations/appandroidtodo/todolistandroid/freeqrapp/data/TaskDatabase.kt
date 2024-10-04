package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.utils.LocalDateTimeConverter

@Database(entities = [Task::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}