package com.amjad.deezerchallange.data.repositories

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.amjad.deezerchallange.data.paging.AlbumDataSourceFactory
import com.amjad.deezerchallange.data.paging.ArtistDataSourceFactory
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.models.ArtistDomainModel
import com.amjad.deezerchallange.domain.repositories.ArtistRepository
import io.reactivex.Observable
import javax.inject.Inject

class ArtistRepositoryImp @Inject constructor(
    private val artistDataSourceFactory: ArtistDataSourceFactory,
    private val albumDataSourceFactory: AlbumDataSourceFactory
) : ArtistRepository {
    override fun searchArtist(name: String, page: Int): Observable<PagedList<ArtistDomainModel>> {
        artistDataSourceFactory.setSearchParameter(name)
        return RxPagedListBuilder(artistDataSourceFactory, 25)
            .buildObservable()
    }

    override fun getArtistsAlbums(id: String, page: Int): Observable<PagedList<AlbumDomainModel>> {
        albumDataSourceFactory.setAlbumIdParameter(id)
        return RxPagedListBuilder(albumDataSourceFactory, 25)
            .buildObservable()
    }

}