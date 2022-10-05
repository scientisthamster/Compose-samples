package com.scientifichamster.domain.mapper

import com.scientifichamster.domain.model.LaunchDomainModel
import javax.inject.Inject

interface LaunchDomainFilter {
    fun filter(
        launchesDomainModel: List<LaunchDomainModel>,
        filterYear: Int,
        ascendantOrder: Boolean
    ): List<LaunchDomainModel>
}

class LaunchDomainFilterImpl @Inject constructor() : LaunchDomainFilter {
    override fun filter(
        launchesDomainModel: List<LaunchDomainModel>,
        filterYear: Int,
        ascendantOrder: Boolean
    ): List<LaunchDomainModel> = if (filterYear > 0) {
        val launch = launchesDomainModel.filter { launchDomainModel ->
            launchDomainModel.launchDate.year == filterYear
        }.filter { launchDomainModel ->
            launchDomainModel.launchSuccess
        }
        order(launch, ascendantOrder)
    } else {
        launchesDomainModel
    }

    private fun order(
        launch: List<LaunchDomainModel>,
        ascendantOrder: Boolean
    ): List<LaunchDomainModel> = if (ascendantOrder) {
        launch.sortedBy { launchDomainModel ->
            launchDomainModel.launchDate.monthOfYear
        }.sortedBy { launchDomainModel ->
            launchDomainModel.launchDate.dayOfMonth
        }
    } else {
        launch.sortedByDescending { launchDomainModel ->
            launchDomainModel.launchDate.monthOfYear
        }.sortedByDescending { launchDomainModel ->
            launchDomainModel.launchDate.dayOfMonth
        }
    }
}