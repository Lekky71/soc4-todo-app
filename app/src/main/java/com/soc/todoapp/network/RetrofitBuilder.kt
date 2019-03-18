package com.soc.todoapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://4dec4f78.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}