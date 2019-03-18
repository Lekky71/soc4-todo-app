package com.soc.todoapp.network.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateTodoSuccessResponse {

    @SerializedName("message")
    @Expose
    var message: String? = null

    constructor() {}

    constructor(message: String) : super() {
        this.message = message
    }

}