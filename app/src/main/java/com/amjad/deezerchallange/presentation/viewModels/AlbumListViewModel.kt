package com.amjad.deezerchallange.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.useCases.GetAlbumDetailsUseCase
import com.amjad.deezerchallange.domain.useCases.GetArtistsAlbumsUseCase
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(private val getArtistsAlbumsUseCase: GetArtistsAlbumsUseCase, private val getAlbumDetailsUseCase: GetAlbumDetailsUseCase) :ViewModel(){
    private var albumList :MutableLiveData<PagedList<AlbumDomainModel>> ?=null

    fun getAlbumList(artistId:String):LiveData<PagedList<AlbumDomainModel>> {
        if(albumList==null){
            albumList=MutableLiveData()
            getArtistsAlbumsUseCase.execute(artistId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    albumList!!.postValue(it)
                },{

                })
        }
        return albumList!!
    }

    fun getAlbumDetails(albumId:Int):Observable<AlbumDomainModel>{
        return getAlbumDetailsUseCase.execute(albumId.toString())
    }

}