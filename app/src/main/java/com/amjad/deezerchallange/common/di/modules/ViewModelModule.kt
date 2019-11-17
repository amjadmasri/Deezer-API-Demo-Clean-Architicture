package com.amjad.deezerchallange.common.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amjad.deezerchallange.common.di.interfaces.ViewModelKey
import com.amjad.deezerchallange.presentation.viewModels.AlbumDetailsViewModel
import com.amjad.deezerchallange.presentation.viewModels.AlbumListViewModel
import com.amjad.deezerchallange.presentation.viewModels.SearchArtistViewModel
import com.amjad.deezerchallange.presentation.viewModels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchArtistViewModel::class)
    abstract fun bindSearchArtistViewModel(searchArtistViewModel: SearchArtistViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumListViewModel::class)
    abstract fun bindAlbumListViewModel(albumListViewModel: AlbumListViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailsViewModel::class)
    abstract fun bindAlbumDetailsViewModel(albumDetailsViewModel: AlbumDetailsViewModel):ViewModel

}