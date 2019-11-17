package com.amjad.deezerchallange.domain.models

data class ContributorDomainModel (
    val id: Int,
    val name: String,
    val picture: String,
    val pictureBig: String,
    val pictureMedium: String,
    val pictureSmall: String,
    val pictureXl: String,
    val radio: Boolean,
    val role: String,
    val share: String,
    val tracklist: String
)