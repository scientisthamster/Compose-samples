package com.scientifichamster.data_api.data

import com.scientifichamster.data.SpaceXRemoteSource
import com.scientifichamster.data.model.CompanyInfoRepositoryModel
import com.scientifichamster.data.model.LaunchRepositoryModel
import com.scientifichamster.data.model.LinksRepositoryModel
import com.scientifichamster.data.model.RocketRepositoryModel
import com.scientifichamster.data_api.ApiService
import com.scientifichamster.data_api.mapper.CompanyInfoResponseToRepositoryModelMapper
import com.scientifichamster.data_api.mapper.LaunchesResponseToRepositoryModelMapper
import kotlinx.coroutines.flow.*
import org.joda.time.DateTime
import java.net.UnknownHostException
import javax.inject.Inject

class SpaceXRemoteSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val companyInfoRepositoryMapper: CompanyInfoResponseToRepositoryModelMapper,
    private val launchesRepositoryMapper: LaunchesResponseToRepositoryModelMapper
) : SpaceXRemoteSource {

    private val _companyInfoStateFlow = MutableStateFlow(getInitialStateCompanyInfoModel())
    private val companyInfoSharedFlow = _companyInfoStateFlow.asSharedFlow()
    private val _launchModelStateFlow = MutableStateFlow(getInitialStateLaunchModel())
    private val launchModelSharedFlow = _launchModelStateFlow.asSharedFlow()

    override suspend fun getCompanyInfo(): Flow<CompanyInfoRepositoryModel> {
        try {
            companyInfoRepositoryMapper.toRepositoryModel(apiService.getCompanyInfo())
                .let { companyInfoRepositoryModel ->
                    _companyInfoStateFlow.emit(companyInfoRepositoryModel)
                }
        } catch (connectionException: UnknownHostException) {
            throw connectionException
        }
        return companyInfoSharedFlow.distinctUntilChanged()
    }

    override suspend fun getAllLaunches(): Flow<List<LaunchRepositoryModel>> {
        try {
            launchesRepositoryMapper.toRepository(apiService.getAllLaunches())
                .let { list ->
                    _launchModelStateFlow.emit(list)
                }
        } catch (connectionException: UnknownHostException) {
            throw connectionException
        }
        return launchModelSharedFlow.distinctUntilChanged()
    }

    private fun getInitialStateCompanyInfoModel() = CompanyInfoRepositoryModel(
        "", "", "", "", -1, -1,
    )

    private fun getInitialStateLaunchModel() = listOf(
        LaunchRepositoryModel(
            "", DateTime(), RocketRepositoryModel("", ""), LinksRepositoryModel("", "", ""), false
        )
    )
}