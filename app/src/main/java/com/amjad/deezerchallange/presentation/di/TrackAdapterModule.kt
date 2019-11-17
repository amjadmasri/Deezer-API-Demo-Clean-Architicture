package com.amjad.deezerchallange.presentation.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.deezerchallange.presentation.ui.albumDetails.TrackAdapter
import dagger.Module
import dagger.Provides

@Module
class TrackAdapterModule {

    @Provides
    fun provideTracksPagedAdapter(): TrackAdapter {
        return TrackAdapter(mutableListOf())
    }

    @Provides
    internal fun provideLinearLayoutManager(
        context: Context
    ): LinearLayoutManager = LinearLayoutManager(context)



}