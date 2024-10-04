package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp

import android.app.Application
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.di.appModule
import com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)
            modules(appModule)
            modules(dataModule)
        }
    }
}