package com.amjad.deezerchallange.domain.useCases

import androidx.paging.PagedList
import com.amjad.deezerchallange.domain.models.ArtistDomainModel
import com.amjad.deezerchallange.domain.repositories.ArtistRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(private val artistRepository: ArtistRepository) {

    fun execute(name:String):Observable<PagedList<ArtistDomainModel>> {
        return artistRepository.searchArtist(name,0)
    }
}