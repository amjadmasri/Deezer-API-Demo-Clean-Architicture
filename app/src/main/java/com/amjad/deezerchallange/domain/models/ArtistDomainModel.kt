package com.amjad.deezerchallange.domain.models

data class ArtistDomainModel (
    val id: String,
    val name: String,
    val nbAlbum: Int?,
    val nbFan: Int?,
    val picture: String?,
    val pictureBig: String?,
    val pictureMedium: String?,
    val pictureSmall: String?,
    val pictureXl: String?,
    val radio: Boolean?,
    val tracklist: String
)