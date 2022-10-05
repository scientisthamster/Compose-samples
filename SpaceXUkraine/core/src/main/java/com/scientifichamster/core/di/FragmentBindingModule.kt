package com.scientifichamster.core.di

import androidx.fragment.app.FragmentFactory
import com.scientifichamster.core.android.InjectingFragmentFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FragmentBindingModule {
    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}