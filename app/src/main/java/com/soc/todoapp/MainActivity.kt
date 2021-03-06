package com.soc.todoapp

import android.app.Activity
import android.app.ProgressDialog
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.soc.todoapp.data.TodoData
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

        var todo = TodoData()

        if(intent.extras != null){
            todo = intent.getParcelableExtra("EDIT_THIS_TODO")
            titleEditText.setText(todo.title)
            contentEditText.setText(todo.content)
        }

        saveButton.setOnClickListener { v ->
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            val date = System.currentTimeMillis()

            todo.title = title
            todo.content = content
            todo.created_at = date


            val task = SaveAsyncTask(todoViewModel, this, this)
            task.execute(todo)



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


    class SaveAsyncTask(private val todoViewModel: TodoViewModel,
                        private val lifecycleOwner: LifecycleOwner,
                        private var context: Activity)
        : AsyncTask<TodoData, Void, Boolean>() {
        val progressDialog = ProgressDialog(context)
        override fun doInBackground(vararg params: TodoData?): Boolean {
            todoViewModel.insertTodoIntoDb(params[0]!!).observe(lifecycleOwner, Observer {result ->
                todoViewModel.postTodoToServer(params[0]!!).observe(lifecycleOwner, Observer {
                    progressDialog.dismiss()
                    if(it!!){
                        val intent = Intent(context, ListActivity::class.java)
                        context.startActivity(intent)
                        context.finish()
                    }
                    else {
                        Snackbar.make(context.contentEditText, "An error occurred, check your internet", Snackbar.LENGTH_LONG).show()
                    }

                })

            })
            return true
        }

        override fun onPreExecute() {
            progressDialog.setTitle("Save Todo")
            progressDialog.setMessage("Saving your todo, please wait...")
            progressDialog.show()
            super.onPreExecute()
        }

        override fun onPostExecute(result: Boolean?) {

            super.onPostExecute(result)

        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

    }

}
