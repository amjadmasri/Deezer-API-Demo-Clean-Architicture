package com.amjad.deezerchallange.data.paging

import androidx.paging.PageKeyedDataSource
import com.amjad.deezerchallange.common.utilities.UrlExtractor
import com.amjad.deezerchallange.data.mappers.AlbumMapper
import com.amjad.deezerchallange.data.models.Album
import com.amjad.deezerchallange.data.models.PagedListResponse
import com.amjad.deezerchallange.data.remote.AlbumRemoteSource
import com.amjad.deezerchallange.data.remote.ArtistRemoteSource
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class AlbumPagingDataSource @Inject constructor(
    private val artistRemoteSource: ArtistRemoteSource,
    private val albumMapper: AlbumMapper, private val urlExtractor: UrlExtractor
) :
    PageKeyedDataSource<Int, AlbumDomainModel>() {
    private lateinit var id: String
    fun setAlbumIdParameter(id: String) {
        this.id = id
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, AlbumDomainModel>
    ) {
        artistRemoteSource.getArtistAlbums(id, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<PagedListResponse<Album>>> {
                override fun onSuccess(response: Response<PagedListResponse<Album>>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        val items = data?.dataList

                        callback.onResult(albumMapper.mapListFromRemoteToDomain(items!!) as MutableList<AlbumDomainModel>,
                            0,
                            data.nextUrl?.let { urlExtractor.extractPage(data.nextUrl).toInt() })
                    }
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }
            })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, AlbumDomainModel>
    ) {
        artistRemoteSource.getArtistAlbums(id, params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<PagedListResponse<Album>>> {
                override fun onSuccess(response: Response<PagedListResponse<Album>>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        val items = data?.dataList

                        callback.onResult(albumMapper.mapListFromRemoteToDomain(items!!) as MutableList<AlbumDomainModel>,
                            data.nextUrl?.let { urlExtractor.extractPage(data.nextUrl).toInt() })
                    }
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }
            })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, AlbumDomainModel>
    ) {
    }
}