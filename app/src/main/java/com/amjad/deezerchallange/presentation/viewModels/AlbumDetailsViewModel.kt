package com.amjad.deezerchallange.presentation.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amjad.deezerchallange.common.models.Resource
import com.amjad.deezerchallange.domain.models.TrackDomainModel
import com.amjad.deezerchallange.domain.useCases.GetAlbumTracksUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(private val getAlbumTracksUseCase: GetAlbumTracksUseCase) :
    ViewModel() {
    private var trackList: MutableLiveData<Resource<List<TrackDomainModel>>>? = null

    @SuppressLint("CheckResult")
    fun getTrackList(albumId: Int): LiveData<Resource<List<TrackDomainModel>>> {
        if (trackList == null) {
            trackList = MutableLiveData()
            trackList!!.postValue(Resource.loading())
            getAlbumTracksUseCase.execute(albumId.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    trackList!!.postValue(
                        Resource.success(
                            it
                        )
                    )
                }, {

                    trackList!!.postValue(Resource.error(it.localizedMessage!!))
                })
        }
        return trackList!!
    }
}
