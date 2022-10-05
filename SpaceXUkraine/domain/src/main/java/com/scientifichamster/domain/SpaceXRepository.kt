package com.scientifichamster.domain

import com.scientifichamster.domain.model.CompanyInfoDomainModel
import com.scientifichamster.domain.model.LaunchDomainModel
import kotlinx.coroutines.flow.Flow

interface SpaceXRepository {
    suspend fun getCompanyInfo(): Flow<CompanyInfoDomainModel>
    suspend fun getAllLaunches(): Flow<List<LaunchDomainModel>>
}