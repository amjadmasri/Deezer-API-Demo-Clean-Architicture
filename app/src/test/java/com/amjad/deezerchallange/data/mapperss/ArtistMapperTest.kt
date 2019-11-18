package com.amjad.deezerchallange.data.mapperss

import com.amjad.deezerchallange.data.mappers.ArtistMapper
import com.amjad.deezerchallange.data.models.Artist
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArtistMapperTest {

    lateinit var artistMapper: ArtistMapper

    @Before
    fun setUp() {
        artistMapper = ArtistMapper()
    }

    @Test
    fun mapFromRemoteToDomainTest() {
        val obj = Artist(
            "id",
            "link",
            "name",
            1,
            1,
            "picture",
            "pb",
            "pm",
            "ps",
            "px",
            true,
            "tracklist",
            "type"
        )

        val result = artistMapper.mapFromRemoteToDomain(obj)

        assertEquals(obj.id, result?.id)
        assertEquals(obj.name, result?.name)
        assertEquals(obj.nbAlbum, result?.nbAlbum)
        assertEquals(obj.nbFan, result?.nbFan)
        assertEquals(obj.picture, result?.picture)
        assertEquals(obj.pictureBig, result?.pictureBig)
        assertEquals(obj.pictureMedium, result?.pictureMedium)
        assertEquals(obj.pictureSmall, result?.pictureSmall)
        assertEquals(obj.pictureXl, result?.pictureXl)
        assertEquals(obj.radio, result?.radio)
        assertEquals(obj.tracklist, result?.tracklist)


    }

    @Test
    fun mapListFromRemoteToDomainTest() {
        val obj = Artist(
            "id1",
            "link",
            "name",
            1,
            1,
            "picture",
            "pb",
            "pm",
            "ps",
            "px",
            true,
            "tracklist",
            "type"
        )
        val obj2 = Artist(
            "id2",
            "link",
            "name",
            1,
            1,
            "picture",
            "pb",
            "pm",
            "ps",
            "px",
            true,
            "tracklist",
            "type"
        )
        val obj3 = Artist(
            "id3",
            "link",
            "name",
            1,
            1,
            "picture",
            "pb",
            "pm",
            "ps",
            "px",
            true,
            "tracklist",
            "type"
        )
        val list = listOf<Artist>(obj, obj2, obj3)

        val resultList = artistMapper.mapListFromRemoteToDomain(list)

        assertEquals(list.size, resultList.size)

        assertEquals(list[0].id, resultList[0].id)
        assertEquals(list[1].id, resultList[1].id)
    }

    @Test
    fun mapFromRemoteToDomainTestNullResult() {
        val result = artistMapper.mapFromRemoteToDomain(null)

        assertNull(result)
    }

    @Test
    fun mapListFromRemoteToDomainTestEmptyResult() {
        val result = artistMapper.mapListFromRemoteToDomain(emptyList())
        assertEquals(0,result.size)
    }

}