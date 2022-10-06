package com.scientifichamster.data

import com.scientifichamster.data.model.CompanyInfoRepositoryModel
import com.scientifichamster.data.model.LaunchRepositoryModel
import kotlinx.coroutines.flow.Flow

interface SpaceXRemoteSource {
    suspend fun getCompanyInfo(): Flow<CompanyInfoRepositoryModel>
    suspend fun getAllLaunches(): Flow<List<LaunchRepositoryModel>>
}