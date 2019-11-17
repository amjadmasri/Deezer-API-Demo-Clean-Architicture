package com.amjad.deezerchallange.domain.useCases

import com.amjad.deezerchallange.domain.models.TrackDomainModel
import com.amjad.deezerchallange.domain.repositories.AlbumRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAlbumTracksUseCase @Inject constructor(private val albumRepository: AlbumRepository) {

    fun execute(id:String):Observable<List<TrackDomainModel>> =
        albumRepository.getAlbumTrack(id)
}