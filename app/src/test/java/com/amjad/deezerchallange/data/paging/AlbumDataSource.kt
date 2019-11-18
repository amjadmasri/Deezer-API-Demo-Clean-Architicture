package com.amjad.deezerchallange.data.paging

import androidx.paging.PageKeyedDataSource
import androidx.paging.PositionalDataSource
import com.amjad.deezerchallange.common.utilities.UrlExtractor
import com.amjad.deezerchallange.data.mappers.AlbumMapper
import com.amjad.deezerchallange.data.models.Artist
import com.amjad.deezerchallange.data.models.PagedListResponse
import com.amjad.deezerchallange.data.remote.ArtistRemoteSource
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlbumDataSource {

    @Mock
    private lateinit var artistRemoteSource: ArtistRemoteSource

    @Mock
    private lateinit var albumMapper: AlbumMapper

    @Mock
    private lateinit var loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>

    @Mock
    private lateinit var callback: PageKeyedDataSource.LoadInitialCallback<Int, AlbumDomainModel>

    private lateinit var albumPagingDataSource: AlbumPagingDataSource

    @Mock
    private lateinit var response:Response<PagedListResponse<Artist>>

    @Mock
    private lateinit var urlExtractor: UrlExtractor

    @Mock
    private lateinit var pagedListResponse: PagedListResponse<Artist>

    @Before
    fun setUp(){
        albumPagingDataSource = AlbumPagingDataSource(artistRemoteSource,albumMapper,urlExtractor)
    }


    @Test
    fun testSuccessMethodsInvocation(){
        /*
        Mockito.`when`(artistRemoteSource.searchArtist("anything",1)).thenReturn(Single.just(
            Response.success(pagedListResponse)))
        albumPagingDataSource.setAlbumIdParameter("anything")
        albumPagingDataSource.loadInitial(loadInitialParams,callback)

        verify(response).isSuccessful


         */
    }
}