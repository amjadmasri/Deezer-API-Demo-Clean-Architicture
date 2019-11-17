package com.amjad.deezerchallange.data.repositories

import com.amjad.deezerchallange.data.mappers.AlbumMapper
import com.amjad.deezerchallange.data.mappers.TrackMapper
import com.amjad.deezerchallange.data.models.Track
import com.amjad.deezerchallange.data.remote.AlbumRemoteSource
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.models.TrackDomainModel
import com.amjad.deezerchallange.domain.repositories.AlbumRepository
import io.reactivex.Observable
import javax.inject.Inject

class AlbumRepositoryImp @Inject constructor(
    private val albumRemoteSource: AlbumRemoteSource,
    private val albumMapper: AlbumMapper,
    private val trackMapper: TrackMapper
) : AlbumRepository {
    override fun getAlbumDetails(id: String): Observable<AlbumDomainModel> =
        albumRemoteSource.getAlbumDetails(id)
            .flatMapObservable {
                Observable.just(it.body()?.let { album ->
                    albumMapper.mapFromRemoteToDomain(
                        album
                    )
                })
            }

    override fun getAlbumTrack(id: String): Observable<List<TrackDomainModel>> =
        albumRemoteSource.getAlbumTracks(id)
            .flatMapObservable {
                Observable.just(it.body()?.let { trackResponse ->
                    trackMapper.mapListFromRemoteToDomain(trackResponse.dataList)
                })
            }

}