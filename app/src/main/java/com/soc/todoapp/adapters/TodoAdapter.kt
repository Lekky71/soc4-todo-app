package com.soc.todoapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.soc.todoapp.R
import com.soc.todoapp.models.Todo

class TodoAdapter(var context: Context, var todoList: ArrayList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    // Declaring model layout
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.each_todo_layout, parent, false)
        val viewHolder = TodoViewHolder(view)
        return viewHolder
    }

    //Specifying the number of items to show
    override fun getItemCount(): Int {
        return todoList.size
    }

    //connecting each item to the data source
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.titleTextView.text = todo.title
        holder.contentTextView.text = todo.content
    }

    //Specifying the views in the recycler view holder
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        var contentTextView: TextView = itemView.findViewById(R.id.contentTextView)

    }

}