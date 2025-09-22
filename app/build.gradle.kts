import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

val localProps = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) load(file.inputStream())
}
val geminiApiKey: String =
    localProps.getProperty("API_KEY_GOOGLE_GEMINI")
        ?: System.getenv("API_KEY_GOOGLE_GEMINI")
        ?: ""

android {
    namespace = "com.ndt.beproductive"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.ndt.beproductive"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY_GOOGLE_GEMINI", "\"$geminiApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    val lifecycle_version = "2.8.0"
    val arch_version = "2.2.0"
    val hilt_version = "2.48"

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    // https://github.com/xabaras/RecyclerViewSwipeDecorator
    implementation("it.xabaras.android:recyclerview-swipedecorator:1.4")

    // coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //circleview
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.google.code.gson:gson:2.10.1")
    // retrofit2 convert Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("jp.wasabeef:glide-transformations:4.3.0")

    // mpchart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // video sdk
    //VideoSDK
    implementation("live.videosdk:rtc-android-sdk:0.1.25")

    // library to perform Network call to generate a meeting id
    implementation("com.amitshekhar.android:android-networking:1.0.2")
    implementation ("com.google.dagger:hilt-android:$hilt_version")
    kapt ("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation ("androidx.activity:activity-ktx:1.9.0")
    implementation ("androidx.fragment:fragment-ktx:1.8.0")


}