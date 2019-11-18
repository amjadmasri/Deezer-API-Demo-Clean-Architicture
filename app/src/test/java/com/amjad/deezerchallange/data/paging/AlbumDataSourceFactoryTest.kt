package com.amjad.deezerchallange.data.paging

import androidx.paging.DataSource
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDataSourceFactoryTest {

    private lateinit var albumDataSourceFactory :AlbumDataSourceFactory

    @Mock
    private lateinit var albumdataSource: AlbumPagingDataSource

    @Before
    fun setUp(){
        albumDataSourceFactory= AlbumDataSourceFactory(albumdataSource)
    }


    @Test
    fun testNoExceptionIfCreateIsCalledWithName(){
        albumDataSourceFactory.setAlbumIdParameter("id")
        albumDataSourceFactory.create()
    }

    @Test(expected = Exception::class)
    fun testExceptionIfCreateIsCalledWithoutName(){
        albumDataSourceFactory.create()
    }

    @Test
    fun testDataSourceSetParameterIsCalled(){
        albumDataSourceFactory.setAlbumIdParameter("id")
        albumDataSourceFactory.create()

        verify(albumdataSource).setAlbumIdParameter("id")
    }

    @Test
    fun testDataSourceCorrectDataTypeIsReturned(){
        albumDataSourceFactory.setAlbumIdParameter("id")
        val obj =albumDataSourceFactory.create()

        assertThat(obj,instanceOf(DataSource::class.java))
    }



}