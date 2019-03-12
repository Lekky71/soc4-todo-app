package com.soc.todoapp.models

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.soc.todoapp.data.TodoData
import com.soc.todoapp.data.TodoDataDao
import com.soc.todoapp.data.TodoDatabase

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDao: TodoDataDao

    init {
        todoDao = TodoDatabase.getInstance(application)!!.todoDataDao()

    }

    fun insertTodoIntoDb(todo: TodoData){
        todoDao.insertTodo(todo)
    }
}