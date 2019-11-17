package com.amjad.deezerchallange.data.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.amjad.deezerchallange.common.utilities.UrlExtractor
import com.amjad.deezerchallange.data.mappers.ArtistMapper
import com.amjad.deezerchallange.data.models.Artist
import com.amjad.deezerchallange.data.models.PagedListResponse
import com.amjad.deezerchallange.data.remote.ArtistRemoteSource
import com.amjad.deezerchallange.domain.models.ArtistDomainModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ArtistPagingDataSource @Inject constructor(
    private val artistRemoteSource: ArtistRemoteSource,
    private val artistMapper: ArtistMapper, private val urlExtractor: UrlExtractor
) :
    PageKeyedDataSource<Int, ArtistDomainModel>() {
    private lateinit var name: String
    fun setSearchParameter(name: String) {
        this.name = name
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArtistDomainModel>
    ) {
        artistRemoteSource.searchArtist(name, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<PagedListResponse<Artist>>> {
                override fun onSuccess(response: Response<PagedListResponse<Artist>>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        val items = data?.dataList

                        callback.onResult(artistMapper.mapListFromRemoteToDomain(items!!) as MutableList<ArtistDomainModel>,
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
        callback: LoadCallback<Int, ArtistDomainModel>
    ) {
        artistRemoteSource.searchArtist(name, params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<PagedListResponse<Artist>>> {
                override fun onSuccess(response: Response<PagedListResponse<Artist>>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        val items = data?.dataList
                        callback.onResult(artistMapper.mapListFromRemoteToDomain(items!!) as MutableList<ArtistDomainModel>,
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
        callback: LoadCallback<Int, ArtistDomainModel>
    ) {
    }
}