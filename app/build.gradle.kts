import org.jetbrains.kotlin.config.KotlinCompilerVersion

val openWeatherMapKey: String by project

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.1")
    defaultConfig {
        applicationId = "com.elxreno.weather"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "openWeatherMapKey", openWeatherMapKey)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // Kotlin stuff
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KotlinCompilerVersion.VERSION}")

    // AndroidX stuff
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")

    // Material stuff
    implementation("com.google.android.material:material:1.0.0")

    // Tests stuff
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    // Moxy
    val moxyVersion = "1.7.0"
    implementation("tech.schoolhelper:moxy-x:$moxyVersion")
    implementation("tech.schoolhelper:moxy-x-androidx:$moxyVersion")
    kapt("tech.schoolhelper:moxy-x-compiler:$moxyVersion")

    // Anko
    val ankoVersion = "0.10.8"
    implementation("org.jetbrains.anko:anko-commons:$ankoVersion")
    implementation("org.jetbrains.anko:anko-design:$ankoVersion")

    // Retrofit
    val retrofitVersion = "2.6.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    // OkHttp logging
    implementation("com.squareup.okhttp3:logging-interceptor:4.0.1")

    // Room
    val roomVersion = "2.1.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")

    // Dagger
    val daggerVersion = "2.23.2"
    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation("com.google.dagger:dagger-android:$daggerVersion")
    implementation("com.google.dagger:dagger-android-support:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
    kapt("com.google.dagger:dagger-android-processor:$daggerVersion")

    // RxJava
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
}
