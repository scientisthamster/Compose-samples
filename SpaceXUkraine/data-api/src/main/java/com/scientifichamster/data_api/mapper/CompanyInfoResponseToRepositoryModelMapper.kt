package com.scientifichamster.data_api.mapper

import com.scientifichamster.data.model.CompanyInfoRepositoryModel
import com.scientifichamster.data_api.model.CompanyInfoResponse
import javax.inject.Inject

interface CompanyInfoResponseToRepositoryModelMapper {
    fun toRepositoryModel(companyInfoResponse: CompanyInfoResponse): CompanyInfoRepositoryModel
}

class CompanyInfoResponseToRepositoryModelMapperImpl @Inject constructor() :
    CompanyInfoResponseToRepositoryModelMapper {
    override fun toRepositoryModel(companyInfoResponse: CompanyInfoResponse) =
        CompanyInfoRepositoryModel(
            name = companyInfoResponse.name,
            founder = companyInfoResponse.founder,
            founded = companyInfoResponse.founded,
            employees = companyInfoResponse.employees,
            launchSites = companyInfoResponse.launchSites,
            valuation = companyInfoResponse.valuation
        )
}