package com.ndt.beproductive.viewmodel

import androidx.lifecycle.ViewModel
import com.ndt.beproductive.DI.PostRepository2
import com.ndt.beproductive.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostHiltViewModel @Inject constructor(private val repo: PostRepository2): BaseViewModel() {
    fun getPosts(): List<String> {
        return repo.fetchPost()
    }
}