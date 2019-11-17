package com.amjad.deezerchallange.data.mappers

import com.amjad.deezerchallange.data.models.Genre
import com.amjad.deezerchallange.data.models.PagedListResponse
import com.amjad.deezerchallange.domain.models.GenreDomainModel

class GenreMapper {
    fun mapFromRemoteToDomain(genre: Genre):GenreDomainModel= GenreDomainModel(
        genre.id,genre.name,genre.picture
    )

    fun mapListFromRemoteToDomain(list: List<Genre>): List<GenreDomainModel> =
        list.map {
            mapFromRemoteToDomain(it)
        }

    fun mapResponseToDomain(genreResponse: PagedListResponse<Genre>?): List<GenreDomainModel>? =
        genreResponse?.let{mapListFromRemoteToDomain(genreResponse.dataList)}
}