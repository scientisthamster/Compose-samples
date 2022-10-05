package com.scientifichamster.core.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class InjectingViewModelFactory @Inject constructor(private val viewModels: Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}