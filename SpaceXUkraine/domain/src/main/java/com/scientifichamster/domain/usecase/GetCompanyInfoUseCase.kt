package com.scientifichamster.domain.usecase

import com.scientifichamster.domain.SpaceXRepository
import com.scientifichamster.domain.model.CompanyInfoDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCompanyInfoUseCase {
    suspend fun execute(): Flow<CompanyInfoDomainModel>
}

class GetCompanyInfoUseCaseImpl @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) : GetCompanyInfoUseCase {
    override suspend fun execute() = spaceXRepository.getCompanyInfo()
}