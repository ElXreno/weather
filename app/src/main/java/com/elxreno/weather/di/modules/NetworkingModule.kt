package com.elxreno.weather.di.modules

import android.content.Context
import com.elxreno.weather.BuildConfig
import com.elxreno.weather.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideInterceptor(): Interceptor =
        Interceptor { chain ->

            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("appid", BuildConfig.openWeatherMapKey)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

    @Provides
    fun provideCache(context: Context): Cache =
        Cache(context.cacheDir, 10 * 1024 * 1024)

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(AppConstants.OPENWEATHERMAP_KEY)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}