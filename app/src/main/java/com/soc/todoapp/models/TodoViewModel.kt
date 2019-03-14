package com.soc.todoapp.models

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.soc.todoapp.data.TodoData
import com.soc.todoapp.data.TodoDataDao
import com.soc.todoapp.data.TodoDatabase

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDao: TodoDataDao

    init {
        todoDao = TodoDatabase.getInstance(application)!!.todoDataDao()

    }

    fun insertTodoIntoDb(todo: TodoData): LiveData<Boolean>{
        val response = MutableLiveData<Boolean>()
        todoDao.insertTodo(todo)
        response.postValue(true)
        return response
    }

    fun getAllTodos(): LiveData<ArrayList<TodoData>> {
        val response = MutableLiveData<ArrayList<TodoData>>()
        val data = todoDao.getAllTodos()
        response.postValue(data as ArrayList<TodoData>?)
        return response
    }
}