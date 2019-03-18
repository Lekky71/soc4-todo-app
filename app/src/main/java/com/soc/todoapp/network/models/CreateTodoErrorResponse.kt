package com.soc.todoapp.network.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateTodoErrorResponse {

    @SerializedName("error")
    @Expose
    var error: String? = null

    constructor() {}

    constructor(error: String) : super() {
        this.error = error
    }

}