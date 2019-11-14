package com.amjad.deezerchallange.common.di.modules

import androidx.lifecycle.ViewModelProvider
import com.amjad.deezerchallange.presentation.viewModels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}