package com.soc.todoapp.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager {
    var sharedPreferences: SharedPreferences

    private val fileName = "todo_app_shared_preferences"
    private val usernameKey = "username"
    private val loggedInKey = "isLoggedIn"

    constructor(context: Context){
        sharedPreferences = context.getSharedPreferences(fileName, 0)
    }

    // Save a value
    fun setUserName(name: String){
        val editor = sharedPreferences.edit()
        editor.putString(usernameKey, name)
        editor.apply()
    }

    // Get a value
    fun getUsername(): String {
        val name = sharedPreferences.getString(usernameKey, null)
        return name
    }

    fun setLoggedIn(value: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean(loggedInKey, value)
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return this.sharedPreferences.getBoolean(loggedInKey, false)
    }

}