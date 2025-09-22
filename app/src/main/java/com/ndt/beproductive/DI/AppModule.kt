package com.ndt.beproductive.DI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // danh dấu đây là module - nơi cung cấp các instance cho dependencies

/*
    Cac instance o day co sẵn ở cấp độ App và các subcomponent như Act, frag, Viewmodel đều có thể sử dụng
    object được khởi tạo một lần duy nhất trong runtime.
    Module chỉ cần tồn tại duy nhất một instance để Hilt biết cách cung cấp dependency.
    dùng class, bạn phải tạo một instance của class đó, mà Hilt không quản lý việc này tự động → dễ thừa thãi.
 */

/*
    ------ Tác dụng của object AppModule trong luồng DI của hilt --------
    1. Hilt scan qua tất cả các module được @InstallIn
    ----> Khi thấy AppModule Hilt tạo metadata: Nếu ai cần ApiService2 hoặc PostRepository2 thì gọi các hàm @Provides trong đây để cung cấp
    2. Khi cần 1 dependency
    ----> Khi inject vào PostRepository2
    ----> Hilt thấy PostRepository2 cần ApiService2, Hilt tra trong cây graph thấy ApiService2 được cung cấp bởi hàm provideApiService2()
    ----> Hilt gọi hàm provideApiService2() để lấy instance ApiService2 truyền vào providePostRepository2
    Tóm lại: object AppModule sẽ là nhà cung cấp tạo ApiService2 và PostRepository2
    Nếu không có, Hilt sẽ không biết cách tạo các denpendency này.
 */
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides // Annotation đánh dấu hàm này sẽ cung cấp một dependency, Hilt sẽ gọi hàm này để biết cách tạo một ApiService.
    @Singleton
    fun provideApiService2(): ApiService2 = ApiServiceImp()

    @Provides
    @Singleton
    fun providePostRepository2(apiService2: ApiService2): PostRepository2 {
        return PostRepository2(apiService2)
    }
}