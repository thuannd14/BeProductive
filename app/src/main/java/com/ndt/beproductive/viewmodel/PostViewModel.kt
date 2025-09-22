package com.ndt.beproductive.viewmodel

import androidx.lifecycle.ViewModel
import com.ndt.beproductive.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel


class PostViewModel(private val repository: PostRepository): BaseViewModel() {
    fun getPosts(): List<String> {
        return repository.fetchPosts()
    }


}