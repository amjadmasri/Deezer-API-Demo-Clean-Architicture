package com.amjad.deezerchallange.data.remote

import com.amjad.deezerchallange.data.models.Album
import com.amjad.deezerchallange.data.models.PagedListResponse
import com.amjad.deezerchallange.data.models.Track
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class AlbumRemoteSourceImp @Inject constructor(private val apiService: ApiService) :
    AlbumRemoteSource {
    override fun getAlbumDetails(id: String): Single<Response<Album>> {
        return apiService.getAlbumDetails(id)
    }

    override fun getAlbumTracks(id: String): Single<Response<PagedListResponse<Track>>> {
        return apiService.getAlbumTracks(id)
    }
}