package com.amjad.deezerchallange.presentation.ui.albumList


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.amjad.deezerchallange.R
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.presentation.ui.base.BaseFragment
import com.amjad.deezerchallange.presentation.viewModels.AlbumListViewModel
import com.amjad.deezerchallange.presentation.viewModels.ViewModelProviderFactory
import com.google.gson.Gson
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_album_list.*
import javax.inject.Inject
import javax.inject.Provider


/**
 * A simple [Fragment] subclass.
 */
class AlbumsListFragment : BaseFragment(),
    AlbumsPagedAdapter.AlbumAdapterListener {

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun removeLoading() {
        loading.visibility = View.GONE
    }

    private fun showError(message: String?) {
        removeLoading()
        Toast.makeText(activity, message ?: "there was an error", Toast.LENGTH_LONG).show()
    }

    private fun renderData(data: PagedList<AlbumDomainModel>?) {
        removeLoading()
        albumsPagedAdapter.submitList(data)

    }

    override fun getLayoutRes(): Int = R.layout.fragment_album_list

    override fun attachFragmentInteractionListener(context: Context) {
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var albumsPagedAdapter: AlbumsPagedAdapter

    private lateinit var viewModel: AlbumListViewModel

    @Inject
    lateinit var gridLayoutManager: Provider<GridLayoutManager>

    @Inject
    lateinit var gson: Gson

    private lateinit var navController: NavController

    private lateinit var artistId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        navController = Navigation.findNavController(view)

        viewModel = ViewModelProviders.of(this, viewModelProviderFactory)
            .get(AlbumListViewModel::class.java)

        artistId = AlbumsListFragmentArgs.fromBundle(arguments!!).artistId

        viewModel.getAlbumList(artistId)
            .observe(this, Observer {
                albumsPagedAdapter.submitList(it)
            })
    }


    @SuppressLint("CheckResult")
    private fun setupUi() {

        album_recycler.layoutManager = gridLayoutManager.get()
        album_recycler.adapter = albumsPagedAdapter

        albumsPagedAdapter.listener = this

    }


    override fun onAlbumClick(album: AlbumDomainModel?) {
        val albumString = gson.toJson(album)
        navController.navigate(
            AlbumsListFragmentDirections.actionAlbumsListFragmentToAlbumDetailsFragment(
                albumString
            )
        )
    }

    override fun getAlbumDetails(id: Int): Observable<AlbumDomainModel> {
        return viewModel.getAlbumDetails(id)
    }

}
