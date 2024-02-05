package com.example.sciflare_task.service

import com.example.sciflare_task.model.Model
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("bashith-task")
    fun getUserDetails(): Call<List<Model>>
}