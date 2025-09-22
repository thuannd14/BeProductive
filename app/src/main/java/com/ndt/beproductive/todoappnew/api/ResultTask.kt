package com.ndt.beproductive.todoappnew.api

/**
 * thường dùng trong các dự án Android/Kotlin để quản lý trạng thái bất đồng bộ (API call, DB call...).
 * Các class con của nó phải được khai báo trong cùng 1 file.
 * giúp compiler biết toàn bộ các trường hợp có thể xảy ra → rất tiện khi dùng với when.
 *
 */
sealed class ResultTask<out T> {
    object Loading : ResultTask<Nothing>()
    data class Success<T>(val data: T) : ResultTask<T>()
    data class Error(val exception: Throwable) : ResultTask<Nothing>()
}