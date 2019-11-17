package com.amjad.deezerchallange.presentation.ui.albumDetails

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.deezerchallange.R
import com.amjad.deezerchallange.common.models.Status
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.models.TrackDomainModel
import com.amjad.deezerchallange.presentation.ui.base.BaseFragment
import com.amjad.deezerchallange.presentation.viewModels.AlbumDetailsViewModel
import com.amjad.deezerchallange.presentation.viewModels.ViewModelProviderFactory
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_album_details.*
import javax.inject.Inject
import javax.inject.Provider

class AlbumDetailsFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.fragment_album_details

    override fun attachFragmentInteractionListener(context: Context) {

    }

    companion object {
        fun newInstance() = AlbumDetailsFragment()
    }

    private lateinit var viewModel: AlbumDetailsViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var linearLayoutManager: Provider<LinearLayoutManager>

    @Inject
    lateinit var trackAdapter: TrackAdapter

    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        viewModel = ViewModelProviders.of(this, viewModelProviderFactory)
            .get(AlbumDetailsViewModel::class.java)

        val albumData = AlbumDetailsFragmentArgs.fromBundle(arguments!!).albumData

        val album: AlbumDomainModel = gson.fromJson(albumData, AlbumDomainModel::class.java)

        setupUi(album)

        viewModel.getTrackList(album.id)
            .observe(this, Observer {
                when(it.status){
                    Status.SUCCESS-> renderTrackData(it.data)
                    Status.LOADING->showLoading()
                    Status.ERROR->showError(it.message)
                }
            })
    }

    private fun showError(message: String?) {
        loading.hide()
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()

    }

    private fun showLoading() {
        loading.show()
    }

    private fun renderTrackData(data: List<TrackDomainModel>?) {
        loading.hide()
        trackAdapter.setData(data!!)

    }

    private fun setupUi(album: AlbumDomainModel) {
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.elevation=0f
        activity.supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                activity.resources
                    .getColor(R.color.gradientStart)
            )
        )

        Glide.with(album_cover)
            .load(album.coverXl)
            .into(album_cover)

        album_title.text = album.title
        artist_name.text = album.artist?.name

        track_recycler.layoutManager = linearLayoutManager.get()
        track_recycler.adapter = trackAdapter



    }

    override fun onDetach() {
        super.onDetach()

        val activity = activity as AppCompatActivity
        activity.supportActionBar?.elevation=8f
        activity.supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                activity.resources
                    .getColor(R.color.colorPrimary)
            )
        )
    }
}
