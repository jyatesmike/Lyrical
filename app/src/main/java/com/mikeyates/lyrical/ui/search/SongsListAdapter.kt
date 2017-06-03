package com.mikeyates.lyrical.ui.search

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import com.mikeyates.lyrical.base.BaseRecyclerViewAdapter
import com.mikeyates.lyrical.models.TrackModel
import com.mikeyates.lyrical.ui.SongSelectedListener

/**
 * Created by jamesyates on 5/29/17.
 */

class SongsListAdapter(private val _selectedListener: SongSelectedListener?, private val _layout: Int) : BaseRecyclerViewAdapter<TrackModel, SongViewHolder>() {

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder.newInstance(parent, _selectedListener, _layout)
    }

    override fun onBindViewHolder(viewHolder: SongViewHolder, position: Int) {
        super.onBindViewHolder(viewHolder, position)
        //personViewHolder.artistName.setText(_searchResults.get(i).getArtistName());
        //personViewHolder.songName.setText(_searchResults.get(i).getTrackName());
        //Picasso.with(personViewHolder.albumCoverPhoto.getContext()).load(_searchResults.get(i).getArtworkUrl100()).into(personViewHolder.albumCoverPhoto);
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}

