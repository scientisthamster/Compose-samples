package com.scientifichamster.data.mapper

import com.scientifichamster.data.model.LaunchRepositoryModel
import com.scientifichamster.domain.model.LaunchDomainModel
import com.scientifichamster.domain.model.LinksDomainModel
import com.scientifichamster.domain.model.RocketDomainModel
import javax.inject.Inject

interface LaunchesRepositoryToDomainModelMapper {
    fun toDomainModel(launchRepositoryModel: List<LaunchRepositoryModel>): List<LaunchDomainModel>
}

class LaunchesRepositoryToDomainModelMapperImpl @Inject constructor() :
    LaunchesRepositoryToDomainModelMapper {
    override fun toDomainModel(launchRepositoryModel: List<LaunchRepositoryModel>) =
        launchRepositoryModel.map { launchRepositoryModel ->

            val rocketDomainModel = RocketDomainModel(
                rocketName = launchRepositoryModel.rocket.rocketName,
                rocketType = launchRepositoryModel.rocket.rocketType,
            )

            val linksDomainModel = LinksDomainModel(
                missionPatchSmall = launchRepositoryModel.links.missionPatchSmall,
                wikipedia = launchRepositoryModel.links.wikipedia,
                videoLink = launchRepositoryModel.links.videoLink,
            )

            LaunchDomainModel(
                missionName = launchRepositoryModel.missionName,
                launchDate = launchRepositoryModel.launchDateLocal,
                rocket = rocketDomainModel,
                links = linksDomainModel,
                launchSuccess = launchRepositoryModel.launchSuccess,
            )
        }
}