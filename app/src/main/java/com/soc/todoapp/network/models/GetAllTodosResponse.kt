package com.soc.todoapp.network.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetAllTodosResponse {

    @SerializedName("success")
    @Expose
    var isSuccess: Boolean = false
    @SerializedName("length")
    @Expose
    var length: Int = 0
    @SerializedName("todo")
    @Expose
    var todo: List<Todo>? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param todo
     * @param length
     * @param success
     */
    constructor(success: Boolean, length: Int, todo: List<Todo>) : super() {
        this.isSuccess = success
        this.length = length
        this.todo = todo
    }

}