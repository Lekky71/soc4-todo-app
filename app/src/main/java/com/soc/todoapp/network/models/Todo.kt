package com.soc.todoapp.network.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Todo {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("content")
    @Expose
    var content: String? = null
    @SerializedName("date_created")
    @Expose
    var dateCreated: String? = null
    @SerializedName("__v")
    @Expose
    var v: Int = 0

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param content
     * @param id
     * @param v
     * @param title
     * @param dateCreated
     */
    constructor(id: String, title: String, content: String, dateCreated: String, v: Int) : super() {
        this.id = id
        this.title = title
        this.content = content
        this.dateCreated = dateCreated
        this.v = v
    }

}