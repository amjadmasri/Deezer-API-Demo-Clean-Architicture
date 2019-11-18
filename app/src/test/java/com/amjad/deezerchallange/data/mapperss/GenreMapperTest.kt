package com.amjad.deezerchallange.data.mapperss

import com.amjad.deezerchallange.data.mappers.GenreMapper
import com.amjad.deezerchallange.data.models.Genre
import com.amjad.deezerchallange.data.models.PagedListResponse
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GenreMapperTest {

    lateinit var genreMapper: GenreMapper

    @Before
    fun setUp() {
        genreMapper = GenreMapper()
    }

    @Test
    fun mapFromResponse() {
        val genre = Genre(1, "name", "picture", "type")
        val genre2 = Genre(2, "name", "picture", "type")
        val genreList = listOf<Genre>(genre, genre2)
        val obj = PagedListResponse<Genre>(genreList, 2, "url")

        val result = genreMapper.mapResponseToDomain(obj)

        assertEquals(genreList.size, result?.size)
        assertEquals(genreList[0].id, result?.get(0)?.id)
    }
}