package com.soc.todoapp.models

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.soc.todoapp.data.TodoData
import com.soc.todoapp.data.TodoDataDao
import com.soc.todoapp.data.TodoDatabase
import com.soc.todoapp.network.RetrofitBuilder
import com.soc.todoapp.network.TodoEndpointInterface
import com.soc.todoapp.network.models.CreateTodoSuccessResponse
import com.soc.todoapp.network.models.GetAllTodosResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoDao: TodoDataDao = TodoDatabase.getInstance(application)!!.todoDataDao()

    fun insertTodoIntoDb(todo: TodoData): LiveData<Boolean> {
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

    fun postTodoToServer(todo: TodoData): LiveData<Boolean> {
        val res = MutableLiveData<Boolean>()

        val retrofit = RetrofitBuilder.getRetrofit()

        val endpointInterface = retrofit.create(TodoEndpointInterface::class.java)

        val sendTodo = TodoRequest(todo.title, todo.content)
        val call = endpointInterface.sendTodoToServer(sendTodo)

        call.enqueue(object : Callback<CreateTodoSuccessResponse> {
            override fun onResponse(
                call: Call<CreateTodoSuccessResponse>,
                response: Response<CreateTodoSuccessResponse>
            ) {
                val result = response.body()
                if (result == null) {
                    res.postValue(false)
                } else {
                    res.postValue(true)
                }

            }

            override fun onFailure(call: Call<CreateTodoSuccessResponse>, t: Throwable) {
                res.postValue(false)
            }
        })

        return res
    }

    fun getAllTodosFromServer(): LiveData<ArrayList<TodoData>> {
        val res = MutableLiveData<ArrayList<TodoData>>()
        val retrofit = RetrofitBuilder.getRetrofit()

        val endpointInterface = retrofit.create(TodoEndpointInterface::class.java)
        val call = endpointInterface.getAllTodosFromServer()

        call.enqueue(object : Callback<GetAllTodosResponse> {
            override fun onResponse(call: Call<GetAllTodosResponse>, response: Response<GetAllTodosResponse>) {
                if (response.body() != null) {
                    val responseBody = response.body()
                    val allTodos = responseBody!!.todo
                    val responseTodos = ArrayList<TodoData>()
                    allTodos!!.forEach { todo ->
                        responseTodos.add(
                            TodoData(
                                System.currentTimeMillis(),
                                todo.title!!,
                                todo.content!!,
                                System.currentTimeMillis()
                            )
                        )
                    }
                    res.postValue(responseTodos)
                }
                else{
                    res.postValue(null)
                }

            }

            override fun onFailure(call: Call<GetAllTodosResponse>, t: Throwable) {
                res.postValue(null)
            }
        })
        return res
    }
}