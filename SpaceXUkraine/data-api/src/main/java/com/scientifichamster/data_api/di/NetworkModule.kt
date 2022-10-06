package com.scientifichamster.data_api.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.scientifichamster.data_api.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(
    private val baseUrlOverride: String? = null
) {
    @Provides
    @BaseUrl
    fun provideBaseUrl() = baseUrlOverride ?: "https://api.spacexdata.com/v3/"

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        gsonConverterFactory: GsonConverterFactory,
        @BaseUrl baseUrl: String
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)

    @Provides
    @Singleton
    fun provideHttpBuilder() =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }

            readTimeout(RETROFIT_TIMEOUT, TimeUnit.MILLISECONDS)
            connectTimeout(RETROFIT_TIMEOUT, TimeUnit.MILLISECONDS)
        }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(
        gson: Gson
    ) = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    companion object {
        const val RETROFIT_TIMEOUT = 10L
    }
}