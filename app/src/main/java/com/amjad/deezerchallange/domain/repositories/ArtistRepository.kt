package com.amjad.deezerchallange.domain.repositories

import androidx.paging.PagedList
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.models.ArtistDomainModel
import io.reactivex.Observable
import io.reactivex.Single

interface ArtistRepository {

    fun searchArtist(name:String,page:Int): Observable<PagedList<ArtistDomainModel>>

    fun getArtistsAlbums(id:String,page: Int):Observable<PagedList<AlbumDomainModel>>
}