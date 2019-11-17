package com.amjad.deezerchallange.data.mappers

import com.amjad.deezerchallange.data.models.Contributor
import com.amjad.deezerchallange.domain.models.ContributorDomainModel

class ContributerMapper {
    fun mapFromRemoteToDomain(contributor: Contributor): ContributorDomainModel {
        return ContributorDomainModel(
            contributor.id,
            contributor.name,
            contributor.picture,
            contributor.pictureBig,
            contributor.pictureMedium,
            contributor.pictureSmall,
            contributor.pictureXl,
            contributor.radio,
            contributor.role,
            contributor.share,
            contributor.tracklist
        )
    }

    fun mapListFromRemoteToDomain(list: List<Contributor>?): List<ContributorDomainModel>? =
        list?.map {
            mapFromRemoteToDomain(it)
        }

}