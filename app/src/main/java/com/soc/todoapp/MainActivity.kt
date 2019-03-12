package com.soc.todoapp

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.soc.todoapp.data.TodoData
import com.soc.todoapp.data.TodoDatabase
import com.soc.todoapp.models.Todo
import com.soc.todoapp.models.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    lateinit var todoViewModel: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        saveButton.setOnClickListener { v ->
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            val date = System.currentTimeMillis()

            val newTodo = TodoData()
            newTodo.title = title
            newTodo.content = content
            newTodo.created_at = date
            TodoRunnable(todoViewModel, newTodo).run()

        }
    }

    class TodoRunnable(private var todoViewModel: TodoViewModel, private var todo: TodoData): Runnable {

        override fun run() {
            todoViewModel.insertTodoIntoDb(todo)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            R.id.action_share -> {
                Toast.makeText(applicationContext, "You clicked on Share", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
