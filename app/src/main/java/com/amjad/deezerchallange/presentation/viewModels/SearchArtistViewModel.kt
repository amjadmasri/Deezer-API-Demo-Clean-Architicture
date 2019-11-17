package com.amjad.deezerchallange.presentation.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.amjad.deezerchallange.common.models.Resource
import com.amjad.deezerchallange.domain.models.ArtistDomainModel
import com.amjad.deezerchallange.domain.useCases.SearchArtistsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchArtistViewModel @Inject constructor(private val searchArtistsUseCase: SearchArtistsUseCase) :
    ViewModel() {

    private var artistList: MediatorLiveData<Resource<PagedList<ArtistDomainModel>>> =
        MediatorLiveData()
    private val currentSearchString: MutableLiveData<String> = MutableLiveData()


    fun observeSearchResult(): LiveData<Resource<PagedList<ArtistDomainModel>>> {
        artistList.removeSource(currentSearchString)

        artistList.addSource(currentSearchString) { name ->
            searchArtistsUseCase.execute(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    artistList.postValue(Resource.success(it))
                }, {
                    artistList.postValue(Resource.error(it.localizedMessage))
                }
                    , {
                        artistList.postValue(Resource.loading())
                    }
                )
        }
        return artistList
    }

    fun setSearchString(name: String) {
        currentSearchString.value = name
    }

}