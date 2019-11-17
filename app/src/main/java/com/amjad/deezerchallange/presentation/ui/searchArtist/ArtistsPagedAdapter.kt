package com.amjad.deezerchallange.presentation.ui.searchArtist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amjad.deezerchallange.R
import com.amjad.deezerchallange.domain.models.ArtistDomainModel
import com.bumptech.glide.Glide


class ArtistsPagedAdapter(artistDiffCallBacks: ArtistDiffCallBacks) :
    PagedListAdapter<ArtistDomainModel, ArtistsPagedAdapter.ArtistSearchViewHolder>(
        artistDiffCallBacks
    ) {

    lateinit var listener: ArtistAdapterListener

    override fun onBindViewHolder(holder: ArtistSearchViewHolder, position: Int) {
        val artistDataModel: ArtistDomainModel? = getItem(position)

        holder.bindTo(artistDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistSearchViewHolder =
        ArtistSearchViewHolder(parent, listener)

    interface ArtistAdapterListener {

        fun onArtistClick(id: String?)
    }


    class ArtistSearchViewHolder(parent: ViewGroup, private val listener: ArtistAdapterListener) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.artist_search_item, parent, false)
        ) {

        private val artistItem= itemView.findViewById<ConstraintLayout>(R.id.artist_item)
        private val nameText = itemView.findViewById<TextView>(R.id.artist_name)
        private val artistAvatar = itemView.findViewById<ImageView>(R.id.artist_avater)
        private var artistDataModel: ArtistDomainModel? = null


        fun bindTo(artist: ArtistDomainModel?) {
            this.artistDataModel = artist
            nameText.text=artist?.name
            Glide.with(artistAvatar)
                .load(artist?.pictureBig)
                .circleCrop()
                .into(artistAvatar)

            artistItem.setOnClickListener {
                listener.onArtistClick(artist?.id)
            }
        }

    }
}