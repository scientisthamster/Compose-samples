package com.scientifichamster.presentation.mapper

import com.scientifichamster.domain.model.LaunchDomainModel
import com.scientifichamster.presentation.model.LaunchUiModel
import com.scientifichamster.presentation.model.LinksUiModel
import com.scientifichamster.presentation.model.RocketUiModel
import javax.inject.Inject

interface LaunchDomainToUiModelMapper {
    fun toUiModel(launchDomainModel: List<LaunchDomainModel>): List<LaunchUiModel>
}

class LaunchDomainToUiModelMapperImpl @Inject constructor(
    private val dateTransformer: DateTransformer
) : LaunchDomainToUiModelMapper {
    override fun toUiModel(launchDomainModel: List<LaunchDomainModel>) =
        launchDomainModel.map { launchDomainModel ->
            val rocketUiModel = RocketUiModel(
                rocketType = launchDomainModel.rocket.rocketType,
                rocketName = launchDomainModel.rocket.rocketName,
            )
            val linksUiModel = LinksUiModel(
                missionPatchSmall = launchDomainModel.links.missionPatchSmall,
                wikipedia = launchDomainModel.links.wikipedia,
                videoLink = launchDomainModel.links.videoLink,
            )

            LaunchUiModel(
                missionName = launchDomainModel.missionName,
                launchDate = dateTransformer.dateToDateString(launchDomainModel.launchDate),
                isPastLaunch = dateTransformer.isPast(launchDomainModel.launchDate),
                differenceOfDays = dateTransformer.getDifferanceOfDays(launchDomainModel.launchDate),
                rocket = rocketUiModel,
                links = linksUiModel,
                launchSuccess = launchDomainModel.launchSuccess
            )
        }
}