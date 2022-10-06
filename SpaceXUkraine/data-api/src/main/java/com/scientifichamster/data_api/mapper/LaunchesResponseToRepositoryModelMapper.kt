package com.scientifichamster.data_api.mapper

import com.scientifichamster.data.model.LaunchRepositoryModel
import com.scientifichamster.data.model.LinksRepositoryModel
import com.scientifichamster.data.model.RocketRepositoryModel
import com.scientifichamster.data_api.model.LaunchesResponse
import javax.inject.Inject

interface LaunchesResponseToRepositoryModelMapper {
    fun toRepository(launchesResponse: List<LaunchesResponse>): List<LaunchRepositoryModel>
}

const val DEFAULT_PATCH = "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png"

class LaunchesResponseToRepositoryModelMapperImpl @Inject constructor(
    private val dateFormatter: DateFormatter
) : LaunchesResponseToRepositoryModelMapper {
    override fun toRepository(launchesResponse: List<LaunchesResponse>): List<LaunchRepositoryModel> =
        launchesResponse.map { launchesResponse ->

            val rocketRepositoryModel = RocketRepositoryModel(
                rocketName = launchesResponse.rocket.rocketName,
                rocketType = launchesResponse.rocket.rocketType,
            )

            val linksRepositoryModel = LinksRepositoryModel(
                missionPatchSmall = launchesResponse.links.missionPatchSmall ?: DEFAULT_PATCH,
                wikipedia = launchesResponse.links.wikipedia.orEmpty(),
                videoLink = launchesResponse.links.videoLink.orEmpty(),
            )

            LaunchRepositoryModel(
                missionName = launchesResponse.missionName,
                launchDateLocal = dateFormatter.format(launchesResponse.launchDate),
                rocket = rocketRepositoryModel,
                links = linksRepositoryModel,
                launchSuccess = launchesResponse.launchSuccess
            )
        }
}