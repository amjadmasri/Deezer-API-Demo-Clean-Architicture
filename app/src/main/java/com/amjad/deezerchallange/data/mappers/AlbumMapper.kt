package com.amjad.deezerchallange.data.mappers

import com.amjad.deezerchallange.data.models.Album
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import javax.inject.Inject

class AlbumMapper @Inject constructor(
    private val artistMapper: ArtistMapper,
    private val genreMapper: GenreMapper,
    private val contributorsMapper: ContributerMapper,
    private val trackMapper: TrackMapper
) {
    fun mapFromRemoteToDomain(album: Album): AlbumDomainModel = AlbumDomainModel(
        artistMapper.mapFromRemoteToDomain(album.artist),
        album.available,
        contributorsMapper.mapListFromRemoteToDomain(album.contributors),
        album.cover,
        album.coverBig,
        album.coverMedium,
        album.coverSmall,
        album.coverXl,
        album.duration,
        album.explicitContentCover,
        album.explicitContentLyrics,
        album.explicitLyrics,
        album.fans,
        album.genreId,
        genreMapper.mapResponseToDomain(album.genresResponse),
        album.id,
        album.label,
        album.nbTracks,
        album.rating,
        album.recordType,
        album.releaseDate,
        album.share,
        album.title,
        album.tracklist,
        trackMapper.mapResponseToDomain(album.tracksResponse),
        album.upc
    )

    fun mapListFromRemoteToDomain(list: List<Album>) :List<AlbumDomainModel> =
        list.map {
            mapFromRemoteToDomain(it)
        }
}