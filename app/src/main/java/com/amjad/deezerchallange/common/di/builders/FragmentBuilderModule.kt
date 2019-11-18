package com.amjad.deezerchallange.common.di.builders


import com.amjad.deezerchallange.presentation.di.AlbumAdapterModule
import com.amjad.deezerchallange.presentation.di.ArtistAdapterModule
import com.amjad.deezerchallange.presentation.di.TrackAdapterModule
import com.amjad.deezerchallange.presentation.ui.albumDetails.AlbumDetailsFragment
import com.amjad.deezerchallange.presentation.ui.albumList.AlbumsListFragment
import com.amjad.deezerchallange.presentation.ui.searchArtist.SearchArtistsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [ArtistAdapterModule::class])
    abstract fun contributeSearchArtistsFragment(): SearchArtistsFragment

    @ContributesAndroidInjector(modules = [AlbumAdapterModule::class])
    abstract fun contributeAlbumListFragment(): AlbumsListFragment

    @ContributesAndroidInjector(modules = [TrackAdapterModule::class])
    abstract fun contributeAlbumDetailsFragment(): AlbumDetailsFragment
}