package com.amjad.deezerchallange.common.di.modules

import android.content.Context

import com.amjad.deezerchallange.common.DeezerApplication
import com.amjad.deezerchallange.common.di.interfaces.ApiUrlInfo
import com.amjad.deezerchallange.common.di.interfaces.DateFormatInfo
import com.amjad.deezerchallange.common.utilities.AppConstants
import com.amjad.deezerchallange.data.remote.ApiService

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {


    @Provides
    @ApiUrlInfo
    fun provideApiURlInfo(): String = AppConstants.API_BASE_URL

    @Provides
    @DateFormatInfo
    fun provideDateFormat(): String = AppConstants.DATE_FORMAT

    @Provides
    @Singleton
    fun provideContext(application: DeezerApplication): Context = application


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory, @ApiUrlInfo baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create<ApiService>(ApiService::class.java)


    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()


    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)


    @Singleton
    @Provides
    fun provideGson(@DateFormatInfo dateFormat: String): Gson = GsonBuilder()
        .setLenient()
        .setDateFormat(dateFormat)
        .create()

}