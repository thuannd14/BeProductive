package com.ndt.beproductive

import com.ndt.beproductive.api.ApiService
import com.ndt.beproductive.repository.PostRepository
import com.ndt.beproductive.viewmodel.PostViewModel

object Injector {
    fun provideApiService(): ApiService = ApiService()

    fun providePostRepository(): PostRepository =
        PostRepository(provideApiService())

    fun providePostViewModel(): PostViewModel =
        PostViewModel(providePostRepository())
}