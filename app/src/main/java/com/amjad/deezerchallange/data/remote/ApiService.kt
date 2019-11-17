package com.amjad.deezerchallange.data.remote

import com.amjad.deezerchallange.data.models.Album
import com.amjad.deezerchallange.data.models.Artist
import com.amjad.deezerchallange.data.models.PagedListResponse
import com.amjad.deezerchallange.data.models.Track
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/artist")
    fun searchArtist(@Query("q")artistName:String ,@Query("index") pageNumber:Int):Single<Response<PagedListResponse<Artist>>>

    @GET("artist/{artist_id}/albums")
    fun getArtistAlbums(@Path("artist_id") artistId:String,@Query("index") pageNumber:Int):Single<Response<PagedListResponse<Album>>>

    @GET("album/{album_id}")
    fun getAlbumDetails(@Path("album_id") albumId:String):Single<Response<Album>>

    @GET("/album/{album_id}/tracks")
    fun getAlbumTracks(@Path("album_id") albumId:String):Single<Response<PagedListResponse<Track>>>
}