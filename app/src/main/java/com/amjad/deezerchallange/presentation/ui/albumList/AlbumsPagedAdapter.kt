package com.amjad.deezerchallange.presentation.ui.albumList

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amjad.deezerchallange.R
import com.amjad.deezerchallange.data.models.Contributor
import com.amjad.deezerchallange.domain.models.AlbumDomainModel
import com.amjad.deezerchallange.domain.models.ContributorDomainModel
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AlbumsPagedAdapter(albumDiffCallBacks: AlbumDiffCallBacks) :
    PagedListAdapter<AlbumDomainModel, AlbumsPagedAdapter.AlbumSearchViewHolder>(
        albumDiffCallBacks
    ) {

    lateinit var listener: AlbumAdapterListener

    override fun onBindViewHolder(holder: AlbumSearchViewHolder, position: Int) {
        val albumDataModel: AlbumDomainModel? = getItem(position)

        holder.bindTo(albumDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumSearchViewHolder =
        AlbumSearchViewHolder(parent, listener)

    interface AlbumAdapterListener {

        fun onAlbumClick(album: AlbumDomainModel?)
        fun getAlbumDetails(id:Int):Observable<AlbumDomainModel>
    }


    class AlbumSearchViewHolder(parent: ViewGroup, private val listener: AlbumAdapterListener) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.album_list_item, parent, false)
        ) {
        private val albumItem = itemView.findViewById<ConstraintLayout>(R.id.album_item)
        private val albumNameText = itemView.findViewById<TextView>(R.id.album_name)
        private val artistName = itemView.findViewById<TextView>(R.id.artist_name)
        private val albumCover = itemView.findViewById<ImageView>(R.id.album_cover)
        private var albumDataModel: AlbumDomainModel? = null


        @SuppressLint("CheckResult")
        fun bindTo(albumDataModel: AlbumDomainModel?) {
            this.albumDataModel = albumDataModel
            albumNameText.text = albumDataModel?.title

            Glide.with(albumCover)
                .load(albumDataModel?.coverBig)
                .into(albumCover)
            listener.getAlbumDetails(albumDataModel!!.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { albumFullDetails->
                    albumItem.setOnClickListener {
                        listener.onAlbumClick(albumFullDetails)
                    }
                   val names = (albumFullDetails.contributors?.map { contributorDomainModel -> contributorDomainModel.name })
                   artistName.text=names?.joinToString(", ")
                }
        }

    }
}