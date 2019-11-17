package com.amjad.deezerchallange.presentation.ui.albumDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amjad.deezerchallange.R
import com.amjad.deezerchallange.domain.models.TrackDomainModel


class TrackAdapter constructor(private val trackList: MutableList<TrackDomainModel>) :
    RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder =
        TrackViewHolder(parent)


    override fun getItemCount(): Int =
        trackList.size


    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bindViewHolder(trackList[position])
    }

    fun setData(newData: List<TrackDomainModel>) {
        trackList.clear()
        trackList.addAll(newData)
        notifyDataSetChanged()
    }


    class TrackViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.track_item, parent, false
        )
    ) {
        private val trackNumber=itemView.findViewById<TextView>(R.id.track_number)
        private val trackTitle = itemView.findViewById<TextView>(R.id.track_title)
        private val artistName = itemView.findViewById<TextView>(R.id.artist_name)
        fun bindViewHolder(trackDomainModel: TrackDomainModel) {
            trackNumber.text=trackDomainModel.trackPosition.toString()
            trackTitle.text = trackDomainModel.title
            artistName.text = trackDomainModel.artist?.name
        }

    }
}