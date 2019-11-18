package com.amjad.deezerchallange.domain.useCases

import androidx.paging.PagedList
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.repositories.ArtistRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetArtistsAlbumsUseCase @Inject constructor(private val artistRepository: ArtistRepository) :UseCase<String,PagedList<AlbumDomainModel>>{

    override fun execute(artistId: String): Observable<PagedList<AlbumDomainModel>> {
        return artistRepository.getArtistsAlbums(artistId, 0)
    }
}