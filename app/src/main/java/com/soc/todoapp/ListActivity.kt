package com.soc.todoapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.soc.todoapp.adapters.TodoAdapter
import com.soc.todoapp.data.TodoData
import com.soc.todoapp.models.TodoViewModel
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var todoViewModel: TodoViewModel



    var todoList: ArrayList<TodoData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)


        //to plug in our data using our TodoApter to the todoRecyclerView
        todoRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val adapter = TodoAdapter(applicationContext, todoList)
        todoRecyclerView.adapter = adapter


        val fetchThread = Thread(Runnable {
            todoViewModel.getAllTodos().observe(this, Observer { allTodos ->
                adapter.swapTodos(allTodos)
            })
        })
        fetchThread.start()


        /*
             created instance of PreferenceManager
             testing
            val preferenceManager = PreferenceManager(applicationContext)
            preferenceManager.setUserName("Promise")
            Snackbar.make(todoRecyclerView, "Welcome ${preferenceManager.getUsername()}!!!", Snackbar.LENGTH_INDEFINITE).show()
            if(preferenceManager.isUserLoggedIn()){
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }

        */

        addTodoButton.setOnClickListener {

            /*
                go to another activity
                how? => We use intents.

                1. Explicit Intents -> specify the next activity
                2. Implicit Intents -> we let the system/user decide.

             */
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)



            /*
                So how do we display list data in out recycler view?
                We use an Adapter
                How does it work?
                I have;
                a. Data source
                b. Recycler View with a model layout
                We use an adapter to connect them together.
             */

            /*
                TODO: Assignment
                1. Implement Tod0 screen layout
                2. Add edit FloatingActionButton
                3. Implement editing functionality
                4. Update the T0d0 in the database
             */
        }
    }
}
