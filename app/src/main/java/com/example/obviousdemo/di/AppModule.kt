package com.example.obviousdemo.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.example.obviousdemo.data.ImageRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
//    val interceptor = run {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    @Provides
//    @Singleton
//    fun providesOkHttpClient() = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

//    @Provides
//    @Singleton
//    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(Constants.BASE_URL)
//        .build()
//
//    @Provides
//    @Singleton
//    fun providesApiService(retrofit: Retrofit) = retrofit.create(APIService::class.java)

    @Provides
    @Singleton
    fun providesImageRemoteSource(@ApplicationContext context: Context) = ImageRemoteSource(context)


}