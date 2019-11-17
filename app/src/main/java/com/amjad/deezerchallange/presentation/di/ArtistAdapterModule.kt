package com.amjad.deezerchallange.presentation.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.deezerchallange.presentation.ui.searchArtist.ArtistDiffCallBacks
import com.amjad.deezerchallange.presentation.ui.searchArtist.ArtistsPagedAdapter
import dagger.Module
import dagger.Provides

@Module
class ArtistAdapterModule {

    @Provides
    fun provideArtistsPagedAdapter(artistDiffCallBacks: ArtistDiffCallBacks): ArtistsPagedAdapter {
        return ArtistsPagedAdapter(artistDiffCallBacks)
    }

    @Provides
    internal fun provideLinearLayoutManager(
        context: Context
    ): LinearLayoutManager = LinearLayoutManager(context)


    @Provides
    fun provideArtistDiffCallBacks(): ArtistDiffCallBacks {
        return ArtistDiffCallBacks()
    }
}