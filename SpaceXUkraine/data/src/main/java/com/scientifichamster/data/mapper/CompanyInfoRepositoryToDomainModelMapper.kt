package com.scientifichamster.data.mapper

import com.scientifichamster.data.model.CompanyInfoRepositoryModel
import com.scientifichamster.domain.model.CompanyInfoDomainModel
import javax.inject.Inject

interface CompanyInfoRepositoryToDomainModelMapper {
    fun toDomainModel(companyInfoRepositoryModel: CompanyInfoRepositoryModel): CompanyInfoDomainModel
}

class CompanyInfoRepositoryToDomainModelMapperImpl @Inject constructor(): CompanyInfoRepositoryToDomainModelMapper {
    override fun toDomainModel(companyInfoRepositoryModel: CompanyInfoRepositoryModel) =
        CompanyInfoDomainModel(
            name = companyInfoRepositoryModel.name,
            founded = companyInfoRepositoryModel.founded,
            founder = companyInfoRepositoryModel.founder,
            employees = companyInfoRepositoryModel.employees,
            launchSites = companyInfoRepositoryModel.launchSites,
            valuation = companyInfoRepositoryModel.valuation
        )
}