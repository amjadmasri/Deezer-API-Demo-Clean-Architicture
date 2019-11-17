package com.amjad.deezerchallange.domain.useCases

import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.repositories.AlbumRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAlbumDetailsUseCase @Inject constructor(private val albumRepository: AlbumRepository) {

    fun execute(albumId:String) :Observable<AlbumDomainModel> {
        return albumRepository.getAlbumDetails(albumId)
    }
}