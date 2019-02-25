package com.soc.todoapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.soc.todoapp.adapters.TodoAdapter
import com.soc.todoapp.models.Todo
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    val titles = arrayOf("toba", "leke", "water", "food", "phone", "charger", "table")
    val contents = arrayOf("jjgknkdsjogfjkdnndsib djdsbjnsdbn nsdifsdnidjfgjdfg",
        "jjgknkdsjogfjkdnndsib djdsbjnsdbn nsdifsdnidjfgjdfg",
        "jjgknkdsjogfjkdnndsib djdsbjnsdbn nsdifsdnidjfgjdfg","jjgknkdsjogfjkdnndsib djdsbjnsdbn nsdifsdnidjfgjdfg",
        "jjgknkdsjogfjkdnndsib djdsbjnsdbn nsdifsdnidjfgjdfg","jjgknkdsjogfjkdnndsib djdsbjnsdbn nsdifsdnidjfgjdfg",
        "jjgknkdsjogfjkdnndsib djdsbjnsdbn nsdifsdnidjfgjdfg")


    var todoList: ArrayList<Todo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        for (i in 0..6){
            todoList.add(Todo(titles[i], contents[i], System.currentTimeMillis()))
        }
        //to plug in our data using our TodoApter to the todoRecyclerView
        todoRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val adapter = TodoAdapter(applicationContext, todoList)
        todoRecyclerView.adapter = adapter

        addTodoButton.setOnClickListener {
            // go to another activity
            // how? => We use intents.
            /*
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
        }
    }
}
