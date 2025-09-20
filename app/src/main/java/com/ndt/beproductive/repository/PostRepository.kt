package com.ndt.beproductive.repository

import com.ndt.beproductive.api.ApiService

class PostRepository(private val apiService: ApiService) {
    fun fetchPosts(): List<String> {
        return apiService.getPosts()
    }
}