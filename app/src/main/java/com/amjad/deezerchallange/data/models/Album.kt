package com.amjad.deezerchallange.data.models


import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("contributors")
    val contributors: List<Contributor>,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("cover_big")
    val coverBig: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_xl")
    val coverXl: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerializedName("fans")
    val fans: Int,
    @SerializedName("genre_id")
    val genreId: Int,
    @SerializedName("genres")
    val genresResponse: PagedListResponse<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("nb_tracks")
    val nbTracks: Int,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("record_type")
    val recordType: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("share")
    val share: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("tracks")
    val tracksResponse: PagedListResponse<Track>,
    @SerializedName("type")
    val type: String,
    @SerializedName("upc")
    val upc: String
)