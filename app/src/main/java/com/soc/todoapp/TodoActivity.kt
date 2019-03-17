package com.soc.todoapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.soc.todoapp.data.TodoData
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.content_todo.*

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        setSupportActionBar(titleToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var todo: TodoData? = null

        if(intent.extras != null){
            todo = intent.getParcelableExtra<TodoData>("TODO_CLICKED")
            supportActionBar!!.title = todo.title
            detailContentTextView.text = todo.content

        }

        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("EDIT_THIS_TODO", todo)
            startActivity(intent)
        }
    }

}
