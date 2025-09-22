package com.ndt.beproductive.todoappnew.model

data class TaskToDo(
    var id: Int,
    var title: String,
    var content: String,
    var isCompleted: Boolean,
    var timeStamp: String
)
