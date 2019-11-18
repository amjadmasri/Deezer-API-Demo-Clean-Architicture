package com.amjad.deezerchallange.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import javax.inject.Inject

class AlbumDataSourceFactory @Inject constructor(private val albumPagingDataSource: AlbumPagingDataSource):
DataSource.Factory<Int,AlbumDomainModel>(){
    private lateinit var albumId: String
    val sourceLiveData = MutableLiveData<AlbumPagingDataSource>()

    override fun create(): DataSource<Int, AlbumDomainModel> {
        require(!albumId.isNullOrEmpty())
        albumPagingDataSource.setAlbumIdParameter(albumId)
        sourceLiveData.postValue(albumPagingDataSource)
        return albumPagingDataSource
    }

    fun setAlbumIdParameter(albumId :String){
        this.albumId=albumId
    }
}