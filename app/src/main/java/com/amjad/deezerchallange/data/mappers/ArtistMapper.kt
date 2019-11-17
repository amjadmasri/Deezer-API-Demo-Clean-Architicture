package com.amjad.deezerchallange.data.mappers

import com.amjad.deezerchallange.data.models.Artist
import com.amjad.deezerchallange.domain.models.ArtistDomainModel

class ArtistMapper {
    fun mapFromRemoteToDomain(artist: Artist?): ArtistDomainModel? = artist?.let { ArtistDomainModel(
        artist.id, artist.name, artist.nbAlbum, artist.nbFan, artist.picture, artist.pictureBig,
        artist.pictureMedium, artist.pictureSmall, artist.pictureXl, artist.radio, artist.tracklist
    ) }

    fun mapListFromRemoteToDomain(list: List<Artist>) : List<ArtistDomainModel> =
        list.map {
            mapFromRemoteToDomain(it)!!
        }
}