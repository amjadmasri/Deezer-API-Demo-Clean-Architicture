package com.amjad.deezerchallange.domain.models

data class TrackDomainModel (
    val artist: ArtistDomainModel?,
    val duration: Int?,
    val explicitContentCover: Int?,
    val explicitContentLyrics: Int?,
    val explicitLyrics: Boolean?,
    val id: Int?,
    val preview: String?,
    val rank: Int?,
    val readable: Boolean?,
    val title: String?,
    val titleShort: String?,
    val titleVersion: String?,
    val trackPosition:Int?,
    val diskNumber:Int?
)