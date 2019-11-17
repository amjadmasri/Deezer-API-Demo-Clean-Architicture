package com.amjad.deezerchallange.data.remote

import com.amjad.deezerchallange.data.models.Album
import com.amjad.deezerchallange.data.models.Artist
import com.amjad.deezerchallange.data.models.PagedListResponse
import io.reactivex.Single
import retrofit2.Response

interface ArtistRemoteSource {

    fun searchArtist(name: String, page: Int): Single<Response<PagedListResponse<Artist>>>

    fun getArtistAlbums(id: String,page: Int): Single<Response<PagedListResponse<Album>>>
}