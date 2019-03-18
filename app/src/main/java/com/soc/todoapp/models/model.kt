package com.soc.todoapp.models

data class TodoRequest(var title:String, var content:String)

data class MinApp(var name: String, var picUri: String, var isMinimizable: Boolean, var isClosable: Boolean, var fullImageUrl: String)