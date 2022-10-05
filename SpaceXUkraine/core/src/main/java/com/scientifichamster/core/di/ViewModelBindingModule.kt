package com.scientifichamster.core.di

import androidx.lifecycle.ViewModelProvider
import com.scientifichamster.core.android.InjectingViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBindingModule {
    @Binds
    abstract fun bindViewModelFactory(factory: InjectingViewModelFactory): ViewModelProvider.Factory
}