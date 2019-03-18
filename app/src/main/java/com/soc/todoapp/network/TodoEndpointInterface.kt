package com.soc.todoapp.network

import com.soc.todoapp.models.TodoRequest
import com.soc.todoapp.network.models.CreateTodoSuccessResponse
import com.soc.todoapp.network.models.GetAllTodosResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TodoEndpointInterface {

    @POST("/")
    fun sendTodoToServer(@Body todoData: TodoRequest): Call<CreateTodoSuccessResponse>

    @GET("/todo/all")
    fun getAllTodosFromServer(): Call<GetAllTodosResponse>

}