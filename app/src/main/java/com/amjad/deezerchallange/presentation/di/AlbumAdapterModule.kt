package com.amjad.deezerchallange.presentation.di

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.amjad.deezerchallange.R
import com.amjad.deezerchallange.presentation.ui.albumList.AlbumDiffCallBacks
import com.amjad.deezerchallange.presentation.ui.albumList.AlbumsPagedAdapter
import com.amjad.deezerchallange.presentation.ui.albumList.AlbumsListFragment
import dagger.Module
import dagger.Provides

@Module
class AlbumAdapterModule {

    @Provides
    fun provideAlbumsPagedAdapter(albumDiffCallBacks: AlbumDiffCallBacks): AlbumsPagedAdapter {
        return AlbumsPagedAdapter(albumDiffCallBacks)
    }

    @Provides
    internal fun provideGridLayoutManager(
        imagesListFragment: AlbumsListFragment,
        context: Context
    ): GridLayoutManager {
        val isPhone = context.resources.getBoolean(R.bool.is_phone)
        return if (isPhone)
            GridLayoutManager(imagesListFragment.activity, 2)
        else
            GridLayoutManager(imagesListFragment.activity, 3)
    }


    @Provides
    fun provideAlbumDiffCallBacks(): AlbumDiffCallBacks {
        return AlbumDiffCallBacks()
    }
}