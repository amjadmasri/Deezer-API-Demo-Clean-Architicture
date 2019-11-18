package com.amjad.deezerchallange.data.remote

import com.amjad.deezerchallange.data.models.Album
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlbumRemoteSourceTest {

    @Mock
    lateinit var apiService: ApiService
    @Mock
    lateinit var response: Response<Album>


    lateinit var albumRemoteSource: AlbumRemoteSourceImp
    @Before
    fun setup() {
        albumRemoteSource = AlbumRemoteSourceImp(apiService)

        `when`(apiService.getAlbumDetails(ArgumentMatchers.anyString())).thenReturn(
            Single.just(
                response
            )
        )
    }

    @Test
    fun testGetAlbumDetailsInvocation() {
        albumRemoteSource.getAlbumDetails("1")
        verify(apiService).getAlbumDetails("1")
    }

    @Test
    fun testGetAlbumDetailsResponseType() {
        albumRemoteSource.getAlbumDetails("1")
            .test()
            .assertComplete()

        verify(apiService).getAlbumDetails("1")
    }

    @Test
    fun testRemoteSourceSuccess() {
        `when`(apiService.getAlbumDetails("1")).thenReturn(Single.just(response))

        albumRemoteSource.getAlbumDetails("1")
            .test()
            .assertValue(response)
    }

    @Test
    fun testRemoteSourceError() {
        val throwable = mock(Throwable::class.java)
        `when`(apiService.getAlbumDetails("1")).thenReturn(Single.error(throwable))

        albumRemoteSource.getAlbumDetails("1")
            .test()
            .assertError(throwable)
    }
}