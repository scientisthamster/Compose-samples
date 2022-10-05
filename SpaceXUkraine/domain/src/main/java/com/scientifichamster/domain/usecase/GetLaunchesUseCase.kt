package com.scientifichamster.domain.usecase

import com.scientifichamster.domain.SpaceXRepository
import com.scientifichamster.domain.mapper.LaunchDomainFilter
import com.scientifichamster.domain.model.LaunchDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetLaunchesUseCase {
    suspend fun execute(
        yearFilterCriteria: Int,
        ascendantOrder: Boolean
    ): Flow<List<LaunchDomainModel>>
}

class GetLaunchesUseCaseImpl @Inject constructor(
    private val spaceXRepository: SpaceXRepository,
    private val launchDomainFilter: LaunchDomainFilter
) : GetLaunchesUseCase {
    override suspend fun execute(
        yearFilterCriteria: Int,
        ascendantOrder: Boolean
    ): Flow<List<LaunchDomainModel>> =
        spaceXRepository.getAllLaunches().map { allLaunchesDomainModel ->
            launchDomainFilter.filter(
                launchesDomainModel = allLaunchesDomainModel,
                filterYear = yearFilterCriteria,
                ascendantOrder = ascendantOrder
            )
        }
}