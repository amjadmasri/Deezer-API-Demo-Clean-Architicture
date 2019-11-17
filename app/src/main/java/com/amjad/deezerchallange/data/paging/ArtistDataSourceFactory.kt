package com.amjad.deezerchallange.data.paging

import androidx.paging.DataSource
import com.amjad.deezerchallange.domain.models.ArtistDomainModel
import javax.inject.Inject

class ArtistDataSourceFactory @Inject constructor(private val artistPagingDataSource: ArtistPagingDataSource):
DataSource.Factory<Int,ArtistDomainModel>(){
    private lateinit var name: String

    override fun create(): DataSource<Int, ArtistDomainModel> {
        artistPagingDataSource.setSearchParameter(name)
        return artistPagingDataSource
    }

    fun setSearchParameter(name :String){
        this.name=name
    }
}