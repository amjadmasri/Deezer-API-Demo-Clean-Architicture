package com.amjad.deezerchallange.data.di

import com.amjad.deezerchallange.data.mappers.*
import com.amjad.deezerchallange.data.remote.AlbumRemoteSource
import com.amjad.deezerchallange.data.remote.AlbumRemoteSourceImp
import com.amjad.deezerchallange.data.remote.ArtistRemoteSource
import com.amjad.deezerchallange.data.remote.ArtistRemoteSourceImp
import com.amjad.deezerchallange.data.repositories.AlbumRepositoryImp
import com.amjad.deezerchallange.data.repositories.ArtistRepositoryImp
import com.amjad.deezerchallange.domain.repositories.AlbumRepository
import com.amjad.deezerchallange.domain.repositories.ArtistRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {


    @Provides
    fun provideAlbumRemoteSource(albumRemoteSourceImp: AlbumRemoteSourceImp): AlbumRemoteSource =
        albumRemoteSourceImp


    @Provides
    fun provideAlbumRepository(albumRepositoryImp: AlbumRepositoryImp): AlbumRepository =
        albumRepositoryImp


    @Provides
    fun provideAlbumMapper(
        artistMapper: ArtistMapper,
        genreMapper: GenreMapper,
        contributorsMapper: ContributerMapper,
        trackMapper: TrackMapper
    ): AlbumMapper = AlbumMapper(artistMapper, genreMapper, contributorsMapper, trackMapper)



    @Provides
    fun provideArtistRemoteSource(artistRemoteSource: ArtistRemoteSourceImp): ArtistRemoteSource =
        artistRemoteSource


    @Provides
    fun provideArtistRepository(artistRepositoryImp: ArtistRepositoryImp): ArtistRepository =
        artistRepositoryImp


    @Provides
    fun provideArtistMapper(): ArtistMapper = ArtistMapper()


    @Provides
    fun provideContributerMapper(): ContributerMapper = ContributerMapper()


    @Provides
    fun provideGenreMapper(): GenreMapper = GenreMapper()


    @Provides
    fun provideTrackMapper(artistMapper: ArtistMapper): TrackMapper = TrackMapper(artistMapper)

}