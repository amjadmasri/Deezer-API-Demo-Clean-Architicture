package com.amjad.deezerchallange.data.remote

import com.amjad.deezerchallange.data.models.Album
import com.amjad.deezerchallange.data.models.Artist
import com.amjad.deezerchallange.data.models.PagedListResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class ArtistRemoteSourceImp @Inject constructor(private val apiService: ApiService) :
    ArtistRemoteSource {
    override fun searchArtist(
        name: String,
        page: Int
    ): Single<Response<PagedListResponse<Artist>>> {
        return apiService.searchArtist(name, page)
    }

    override fun getArtistAlbums(
        id: String,
        page: Int
    ): Single<Response<PagedListResponse<Album>>> {
        return apiService.getArtistAlbums(id, page)
    }
}