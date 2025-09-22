package com.ndt.beproductive.todoappnew.di

import com.ndt.beproductive.todoappnew.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Annotation này đánh dấu class AppModule là một Hilt Module.
 *
 * Module = nơi khai báo cách cung cấp dependencies (tạo object, cấu hình...).
 *
 * Thay vì khởi tạo thủ công bằng Retrofit.Builder() ... ở mỗi nơi, ta khai báo 1 lần trong module.
 */

/**
 * @InstallIn(SingletonComponent::class)
 *
 * Cho Hilt biết phạm vi (scope) mà module này được cài đặt.
 *
 * Ở đây là SingletonComponent → nghĩa là:
 *
 * Tất cả dependencies được cung cấp trong module này sẽ tồn tại trong suốt vòng đời của application.
 *
 * Giống như singleton pattern nhưng quản lý bởi Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Annotation này đánh dấu một function cung cấp dependency.
     *
     * Function provideApiService() trả về ApiService → khi Hilt cần ApiService, nó sẽ gọi hàm này để tạo instance.
     */
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}