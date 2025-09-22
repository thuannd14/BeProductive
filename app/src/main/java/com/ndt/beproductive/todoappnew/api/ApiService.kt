package com.ndt.beproductive.todoappnew.api

import com.ndt.beproductive.todoappnew.model.TaskToDo
import okhttp3.internal.concurrent.Task
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTasks(): List<TaskToDo>
}