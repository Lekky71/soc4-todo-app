package com.soc.todoapp.models

data class Todo(var title:String, var content:String, var created_at: Long)

class TestTodo {
    val todoObkect = Todo("Water", "Simple Sturv", 3432543)
}

data class MinApp(var name: String, var picUri: String, var isMinimizable: Boolean, var isClosable: Boolean, var fullImageUrl: String)