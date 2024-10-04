package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.STATE
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.TASK_DESCRIPTION
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.TASK_NAME
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.TIME_LAST_VIEWED
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.constants.Constants.TIME_TO_CREATED
import java.time.LocalDateTime

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = STATE) var state: Boolean,
    @ColumnInfo(name = TASK_NAME) var taskName: String,
    @ColumnInfo(name = TASK_DESCRIPTION) var taskDescription: String,
    @ColumnInfo(name = TIME_TO_CREATED) val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = TIME_LAST_VIEWED) var lastViewedAt: LocalDateTime = LocalDateTime.now()
)