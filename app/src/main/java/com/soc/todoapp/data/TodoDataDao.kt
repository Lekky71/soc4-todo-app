package com.soc.todoapp.data

import android.arch.persistence.room.*

@Dao
interface TodoDataDao {

    @Query("SELECT * FROM todos")
    fun getAllTodos(): List<TodoData>

    @Query("SELECT * FROM todos where id=:todoId")
    fun getTodoById(todoId: String): TodoData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todo: TodoData)

    @Delete
    fun deleteTodo(todo: TodoData)
}