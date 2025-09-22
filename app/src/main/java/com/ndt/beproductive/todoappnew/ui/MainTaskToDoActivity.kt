package com.ndt.beproductive.todoappnew.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ndt.beproductive.R
import com.ndt.beproductive.todoappnew.api.ResultTask
import com.ndt.beproductive.todoappnew.viewmodel.TaskToDoViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainTaskToDoActivity: AppCompatActivity() {
    private val viewModel: TaskToDoViewModel by viewModels()
    private lateinit var text: TextView
    private var coroutineLaunched = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task_to_do)
        text = findViewById(R.id.tvTaskToDo)
    }

    override fun onStart() {
        super.onStart()
        if (!coroutineLaunched){
            coroutineLaunched = true
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.uiState.collect{
                    when(it){
                        is ResultTask.Loading ->{
                            launch {
                                showLoading()
                            }
                        }

                        is ResultTask.Error -> {
                            launch {
                                hideLoading()
                                showError(it.exception.message ?: "Unknown error")
                            }
                        }
                        is ResultTask.Success -> {
                            launch {
                                hideLoading()
                                withContext(Dispatchers.Main) {
                                    text.text = it.data.toString()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private fun showLoading() {
        println("Loading...")
    }

    private fun hideLoading() {
        println("Hide loading")
    }

    private fun showError(msg: String) {
        println("Error: $msg")
    }
}