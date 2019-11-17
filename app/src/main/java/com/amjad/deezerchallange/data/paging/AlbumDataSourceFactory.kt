package com.amjad.deezerchallange.data.paging

import androidx.paging.DataSource
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import javax.inject.Inject

class AlbumDataSourceFactory @Inject constructor(private val albumPagingDataSource: AlbumPagingDataSource):
DataSource.Factory<Int,AlbumDomainModel>(){
    private lateinit var albumId: String

    override fun create(): DataSource<Int, AlbumDomainModel> {
        albumPagingDataSource.setAlbumIdParameter(albumId)
        return albumPagingDataSource
    }

    fun setAlbumIdParameter(albumId :String){
        this.albumId=albumId
    }
}