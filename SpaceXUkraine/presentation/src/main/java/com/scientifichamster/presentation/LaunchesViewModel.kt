package com.scientifichamster.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scientifichamster.core.event.Event
import com.scientifichamster.core.event.eventOf
import com.scientifichamster.domain.usecase.GetLaunchesUseCase
import com.scientifichamster.presentation.mapper.LaunchDomainToUiModelMapper
import com.scientifichamster.presentation.model.LaunchUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

abstract class LaunchesViewModel : ViewModel() {
    abstract fun launches(yearFilterCriteria: Int = -1, ascendantOrder: Boolean = false)
    abstract fun openLink(link: String)
    abstract fun onFilterClicked()

    abstract val launches: LiveData<List<LaunchUiModel>>
    abstract val loadingLaunches: LiveData<Boolean>
    abstract val launchesError: LiveData<Event<Unit>>
    abstract val openLink: LiveData<Event<String>>
    abstract val showDialog: LiveData<Event<Unit>>
}

class LaunchesViewModelImpl @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase,
    private val launchDomainToUiModelMapper: LaunchDomainToUiModelMapper
) : LaunchesViewModel() {

    override val launches = MutableLiveData<List<LaunchUiModel>>()
    override val loadingLaunches = MutableLiveData<Boolean>()
    override val launchesError = MutableLiveData<Event<Unit>>()
    override val openLink = MutableLiveData<Event<String>>()
    override val showDialog = MutableLiveData<Event<Unit>>()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        loadingLaunches.value = false
    }

    override fun launches(yearFilterCriteria: Int, ascendantOrder: Boolean) {
        loadingLaunches.value = true
        try {
            viewModelScope.launch {
                getLaunchesUseCase.execute(yearFilterCriteria, ascendantOrder)
                    .catch { throwable ->
                        handleException(throwable)
                    }
                    .collect { launchDomainModel ->
                        val launchUiModel = launchDomainToUiModelMapper.toUiModel(launchDomainModel)
                        launches.postValue(launchUiModel)
                        loadingLaunches.value = false
                    }
            }
        } catch (throwable: Throwable) {
            handleException(throwable)
        }
    }

    override fun openLink(link: String) {
        viewModelScope.launch {
            openLink.postValue(eventOf(link))
        }
    }

    override fun onFilterClicked() {
        viewModelScope.launch {
            showDialog.postValue(eventOf(Unit))
        }
    }

    private fun handleException(throwable: Throwable) {
        Timber.e(throwable)
        launchesError.postValue(eventOf(Unit))
        loadingLaunches.value = false
    }
}