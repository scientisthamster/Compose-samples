package com.scientifichamster.data_api.di

import android.annotation.SuppressLint
import com.scientifichamster.data.SpaceXRemoteSource
import com.scientifichamster.data_api.ApiService
import com.scientifichamster.data_api.data.SpaceXRemoteSourceImpl
import com.scientifichamster.data_api.mapper.*
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Reusable
    fun provideSpaceXRemoteSource(
        apiService: ApiService,
        companyInfoResponseToRepositoryModelMapper: CompanyInfoResponseToRepositoryModelMapper,
        launchesResponseToRepositoryModelMapper: LaunchesResponseToRepositoryModelMapper
    ): SpaceXRemoteSource = SpaceXRemoteSourceImpl(
        apiService,
        companyInfoResponseToRepositoryModelMapper,
        launchesResponseToRepositoryModelMapper
    )

    @Provides
    @Reusable
    fun provideCompanyInfoResponseToRepositoryModelMapper(): CompanyInfoResponseToRepositoryModelMapper =
        CompanyInfoResponseToRepositoryModelMapperImpl()

    @Provides
    @Reusable
    fun provideLaunchesResponseToRepositoryModelMapper(
        dateFormatter: DateFormatter
    ): LaunchesResponseToRepositoryModelMapper =
        LaunchesResponseToRepositoryModelMapperImpl(dateFormatter)

    @Provides
    @Reusable
    fun provideDateFormatter(): DateFormatter = DateFormatterImpl()

    @SuppressLint("ModuleCompanionObjects")
    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        internal fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

        @Provides
        @JvmStatic
        @Singleton
        internal fun provideRetrofit(
            httpBuilder: OkHttpClient.Builder,
            retrofitBuilder: Retrofit.Builder
        ): Retrofit = retrofitBuilder
            .client(httpBuilder.build())
            .build()

    }
}