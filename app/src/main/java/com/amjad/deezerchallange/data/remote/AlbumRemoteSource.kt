package com.amjad.deezerchallange.data.remote

import com.amjad.deezerchallange.data.models.Album
import com.amjad.deezerchallange.data.models.PagedListResponse
import com.amjad.deezerchallange.data.models.Track
import io.reactivex.Single
import retrofit2.Response

interface AlbumRemoteSource {

    fun getAlbumDetails(id: String): Single<Response<Album>>

    fun getAlbumTracks(id:String):Single<Response<PagedListResponse<Track>>>
}