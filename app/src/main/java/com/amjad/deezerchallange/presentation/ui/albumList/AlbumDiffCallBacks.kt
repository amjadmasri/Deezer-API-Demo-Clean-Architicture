package com.amjad.deezerchallange.presentation.ui.albumList

import androidx.recyclerview.widget.DiffUtil
import com.amjad.deezerchallange.domain.models.AlbumDomainModel

class AlbumDiffCallBacks : DiffUtil.ItemCallback<AlbumDomainModel>() {
    override fun areItemsTheSame(
        oldItem: AlbumDomainModel,
        newItem: AlbumDomainModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AlbumDomainModel,
        newItem: AlbumDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}