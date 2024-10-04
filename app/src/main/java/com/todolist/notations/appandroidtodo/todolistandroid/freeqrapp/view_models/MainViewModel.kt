package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.data.Task
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val taskDao: TaskDao) : ViewModel() {

    private val _taskByUid = MutableLiveData<Task>()
    val taskByUid: LiveData<Task> = _taskByUid

    fun getTasksList(): Flow<List<Task>> {
        return taskDao.getAll()
    }

    fun getTaskByUid(uid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _taskByUid.postValue(taskDao.getTaskById(uid))
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.update(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.delete(task)
        }
    }
}