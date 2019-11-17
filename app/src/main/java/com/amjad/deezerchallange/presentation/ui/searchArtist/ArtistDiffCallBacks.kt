package com.amjad.deezerchallange.presentation.ui.searchArtist

import androidx.recyclerview.widget.DiffUtil
import com.amjad.deezerchallange.domain.models.ArtistDomainModel

class ArtistDiffCallBacks : DiffUtil.ItemCallback<ArtistDomainModel>() {
    override fun areItemsTheSame(
        oldItem: ArtistDomainModel,
        newItem: ArtistDomainModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ArtistDomainModel,
        newItem: ArtistDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}