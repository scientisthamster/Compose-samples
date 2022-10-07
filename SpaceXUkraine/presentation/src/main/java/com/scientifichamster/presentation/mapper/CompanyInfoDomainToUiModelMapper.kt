package com.scientifichamster.presentation.mapper

import com.scientifichamster.domain.model.CompanyInfoDomainModel
import com.scientifichamster.presentation.model.CompanyInfoUiModel
import javax.inject.Inject

interface CompanyInfoDomainToUiModelMapper {
    fun toUiModel(companyInfoDomainModel: CompanyInfoDomainModel): CompanyInfoUiModel
}

class CompanyInfoDomainToUiModelMapperImpl @Inject constructor() :
    CompanyInfoDomainToUiModelMapper {
    override fun toUiModel(companyInfoDomainModel: CompanyInfoDomainModel) =
        CompanyInfoUiModel(
            name = companyInfoDomainModel.name,
            founder = companyInfoDomainModel.founder,
            founded = companyInfoDomainModel.founded,
            employees = companyInfoDomainModel.employees,
            launchSites = companyInfoDomainModel.launchSites,
            valuation = companyInfoDomainModel.valuation
        )
}