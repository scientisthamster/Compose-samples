package com.scientifichamster.presentation.di

import com.scientifichamster.presentation.mapper.*
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class PresentationModule {

    @Provides
    @Reusable
    fun provideCompanyInfoDomainToUiModelMapper(): CompanyInfoDomainToUiModelMapper =
        CompanyInfoDomainToUiModelMapperImpl()

    @Provides
    @Reusable
    fun provideLaunchDomainToUiModelMapper(dateTransformer: DateTransformer): LaunchDomainToUiModelMapper =
        LaunchDomainToUiModelMapperImpl(dateTransformer)

    @Provides
    @Reusable
    fun provideDateTransformer(dateTimeProvider: DateTimeProvider): DateTransformer =
        DateTransformerImpl(dateTimeProvider)
}