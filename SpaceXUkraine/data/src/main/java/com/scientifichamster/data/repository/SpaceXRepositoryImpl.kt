package com.scientifichamster.data.repository

import com.scientifichamster.data.SpaceXRemoteSource
import com.scientifichamster.data.mapper.CompanyInfoRepositoryToDomainModelMapper
import com.scientifichamster.data.mapper.LaunchesRepositoryToDomainModelMapper
import com.scientifichamster.domain.SpaceXRepository
import com.scientifichamster.domain.model.CompanyInfoDomainModel
import com.scientifichamster.domain.model.LaunchDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor(
    private val spaceXRemoteSource: SpaceXRemoteSource,
    private val companyInfoDomainMapper: CompanyInfoRepositoryToDomainModelMapper,
    private val launchesDomainMapper: LaunchesRepositoryToDomainModelMapper,
) : SpaceXRepository {

    override suspend fun getCompanyInfo(): Flow<CompanyInfoDomainModel> = supervisorScope {
        spaceXRemoteSource.getCompanyInfo().map { companyInfoRepositoryModel ->
            companyInfoDomainMapper.toDomainModel(companyInfoRepositoryModel)
        }
    }

    override suspend fun getAllLaunches(): Flow<List<LaunchDomainModel>> =
        spaceXRemoteSource.getAllLaunches().map { allLaunchesRepositoryModel ->
            launchesDomainMapper.toDomainModel(allLaunchesRepositoryModel)
        }
}