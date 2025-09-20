package com.ndt.beproductive.DI

import javax.inject.Inject

class PostRepository2 @Inject constructor(
    private val apiService2: ApiService2
){
    fun fetchPost(): List<String> {
        return apiService2.getPost()
    }
}