package com.ndt.beproductive.todoappnew.repository

import com.ndt.beproductive.R
import com.ndt.beproductive.todoappnew.api.ApiService
import com.ndt.beproductive.todoappnew.api.ResultTask
import com.ndt.beproductive.todoappnew.model.TaskToDo
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import okhttp3.internal.concurrent.Task
import javax.inject.Inject
import javax.inject.Singleton

class TaskRepository @Inject constructor(
    private var apiService: ApiService
) {
    fun fetchTask(): Flow<ResultTask<List<TaskToDo>>> = flow{
        emit(ResultTask.Loading) // báo UI đang loading
        try{
            val task = apiService.getTasks()
            emit(ResultTask.Success(task))
        }catch(e : Exception){
            emit(ResultTask.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}