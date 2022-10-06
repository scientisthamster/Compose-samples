package com.scientifichamster.data.di

import com.scientifichamster.data.SpaceXRemoteSource
import com.scientifichamster.data.mapper.CompanyInfoRepositoryToDomainModelMapper
import com.scientifichamster.data.mapper.CompanyInfoRepositoryToDomainModelMapperImpl
import com.scientifichamster.data.mapper.LaunchesRepositoryToDomainModelMapper
import com.scientifichamster.data.mapper.LaunchesRepositoryToDomainModelMapperImpl
import com.scientifichamster.data.repository.SpaceXRepositoryImpl
import com.scientifichamster.domain.SpaceXRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class SpaceXRepositoryModule {

    @Provides
    @Reusable
    fun provideSpaceXRepository(
        spaceXRemoteSource: SpaceXRemoteSource,
        companyInfoRepositoryToDomainModelMapper: CompanyInfoRepositoryToDomainModelMapper,
        launchesRepositoryToDomainModelMapper: LaunchesRepositoryToDomainModelMapper
    ): SpaceXRepository = SpaceXRepositoryImpl(
        spaceXRemoteSource,
        companyInfoRepositoryToDomainModelMapper,
        launchesRepositoryToDomainModelMapper
    )

    @Provides
    @Reusable
    fun provideCompanyInfoRepositoryToDomainModelMapper(): CompanyInfoRepositoryToDomainModelMapper =
        CompanyInfoRepositoryToDomainModelMapperImpl()

    @Provides
    @Reusable
    fun provideLaunchesRepositoryToDomainModelMapper(): LaunchesRepositoryToDomainModelMapper =
        LaunchesRepositoryToDomainModelMapperImpl()
}