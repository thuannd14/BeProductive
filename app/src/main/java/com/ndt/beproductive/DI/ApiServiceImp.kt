package com.ndt.beproductive.DI

class ApiServiceImp: ApiService2 {
    override fun getPost(): List<String> {
        return listOf("Post 1", "Post 2", "Post 3")
    }
}