package com.amjad.deezerchallange.domain.repositories

import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.models.TrackDomainModel
import io.reactivex.Observable

interface AlbumRepository {
    fun getAlbumDetails(id: String): Observable<AlbumDomainModel>

    fun getAlbumTrack(id: String): Observable<List<TrackDomainModel>>
}