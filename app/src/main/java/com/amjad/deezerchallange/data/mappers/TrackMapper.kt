package com.amjad.deezerchallange.data.mappers

import com.amjad.deezerchallange.data.models.PagedListResponse
import com.amjad.deezerchallange.data.models.Track
import com.amjad.deezerchallange.domain.models.TrackDomainModel
import javax.inject.Inject

class TrackMapper @Inject constructor(private val artistMapper: ArtistMapper) {
    fun mapFromRemoteToDomain(track: Track): TrackDomainModel = TrackDomainModel(
        artistMapper.mapFromRemoteToDomain(track.artist),
        track.duration,
        track.explicitContentCover,
        track.explicitContentLyrics,
        track.explicitLyrics,
        track.id,
        track.preview,
        track.rank,
        track.readable,
        track.title,
        track.titleShort,
        track.titleVersion,
        track.trackPosition,
        track.diskNumber
    )

    fun mapListFromRemoteToDomain(list: List<Track>): List<TrackDomainModel> =
        list.map {
            mapFromRemoteToDomain(it)
        }

    fun mapResponseToDomain(genreResponse: PagedListResponse<Track>?): List<TrackDomainModel>? =
       genreResponse?.let { mapListFromRemoteToDomain(genreResponse.dataList) }
}