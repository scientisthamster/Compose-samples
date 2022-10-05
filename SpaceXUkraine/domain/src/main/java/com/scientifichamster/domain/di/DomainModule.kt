package com.scientifichamster.domain.di

import com.scientifichamster.domain.SpaceXRepository
import com.scientifichamster.domain.mapper.LaunchDomainFilter
import com.scientifichamster.domain.mapper.LaunchDomainFilterImpl
import com.scientifichamster.domain.usecase.GetCompanyInfoUseCase
import com.scientifichamster.domain.usecase.GetCompanyInfoUseCaseImpl
import com.scientifichamster.domain.usecase.GetLaunchesUseCase
import com.scientifichamster.domain.usecase.GetLaunchesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class DomainModule {

    @Provides
    @Reusable
    fun provideGetLaunchesUseCase(
        spaceXRepository: SpaceXRepository,
        launchDomainFilter: LaunchDomainFilter
    ): GetLaunchesUseCase = GetLaunchesUseCaseImpl(spaceXRepository, launchDomainFilter)

    @Provides
    @Reusable
    fun provideGetCompanyInfo(
        spaceXRepository: SpaceXRepository
    ): GetCompanyInfoUseCase = GetCompanyInfoUseCaseImpl(spaceXRepository)

    @Provides
    @Reusable
    fun provideLaunchDomainFilter(): LaunchDomainFilter = LaunchDomainFilterImpl()
}