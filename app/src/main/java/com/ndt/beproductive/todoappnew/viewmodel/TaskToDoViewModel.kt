package com.ndt.beproductive.todoappnew.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndt.beproductive.todoappnew.api.ResultTask
import com.ndt.beproductive.todoappnew.repository.TaskRepository
import com.ndt.beproductive.todoappnew.model.TaskToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskToDoViewModel @Inject constructor(
    private var repo: TaskRepository
) : ViewModel()
{
    private val _uiState = MutableStateFlow<ResultTask<List<TaskToDo>>>(ResultTask.Loading)
    val uiState: StateFlow<ResultTask<List<TaskToDo>>> = _uiState

    init {
        loadTask()
    }

    private fun loadTask() {
        viewModelScope.launch {
            repo.fetchTask().collect{ // mỗi lần collector sẽ thực hiện lại từ đầu.
                // sẽ chỉ chạy khi có collector
                _uiState.value = it
            }
        }
    }
}