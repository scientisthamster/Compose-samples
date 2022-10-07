package com.scientifichamster.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scientifichamster.core.event.Event
import com.scientifichamster.core.event.eventOf
import com.scientifichamster.domain.usecase.GetCompanyInfoUseCase
import com.scientifichamster.presentation.mapper.CompanyInfoDomainToUiModelMapper
import com.scientifichamster.presentation.model.CompanyInfoUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

abstract class DashboardViewModel : ViewModel() {
    abstract fun companyInfo()

    abstract val companyInfo: LiveData<CompanyInfoUiModel>
    abstract val loadingCompanyInfo: LiveData<Boolean>
    abstract val companyInfoError: LiveData<Event<Unit>>
}

class DashboardViewModelImpl @Inject constructor(
    private val getCompanyInfoUseCase: GetCompanyInfoUseCase,
    private val companyInfoDomainToUiModelMapper: CompanyInfoDomainToUiModelMapper
) : DashboardViewModel() {

    override val companyInfo = MutableLiveData<CompanyInfoUiModel>()
    override val loadingCompanyInfo = MutableLiveData<Boolean>()
    override val companyInfoError = MutableLiveData<Event<Unit>>()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        loadingCompanyInfo.value = false
    }

    override fun companyInfo() {
        viewModelScope.launch(errorHandler) {
            loadingCompanyInfo.value = true
            try {
                getCompanyInfoUseCase.execute()
                    .catch { throwable ->
                        handleException(throwable)
                    }
                    .collect { companyInfoDomainModel ->
                        val companyInfoUiModel =
                            companyInfoDomainToUiModelMapper.toUiModel(companyInfoDomainModel)
                        companyInfo.postValue(companyInfoUiModel)
                        loadingCompanyInfo.value = false
                    }
            } catch (throwable: Throwable) {
                handleException(throwable)
            }
        }
    }

    private fun handleException(throwable: Throwable) {
        Timber.e(throwable)
        companyInfoError.postValue(eventOf(Unit))
        loadingCompanyInfo.value = false
    }
}