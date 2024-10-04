package com.todolist.notations.appandroidtodo.todolistandroid.freeqrapp.utils

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter {
    private val formatterToString = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromString(value: String?): LocalDateTime? {
        return value?.let {
            LocalDateTime.parse(it, formatterToString)
        }
    }

    @TypeConverter
    fun toString(dateTime: LocalDateTime?): String? {
        return dateTime?.format(formatterToString)
    }
}