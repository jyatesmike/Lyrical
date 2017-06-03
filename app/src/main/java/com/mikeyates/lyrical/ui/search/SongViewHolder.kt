package com.mikeyates.lyrical.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.mikeyates.lyrical.R
import com.mikeyates.lyrical.base.BaseViewHolder
import com.mikeyates.lyrical.models.TrackModel
import com.mikeyates.lyrical.ui.SongSelectedListener
import com.squareup.picasso.Picasso

/**
 * Created by jamesyates on 5/29/17.
 */

class SongViewHolder protected constructor(view: View, private val _selectedListener: SongSelectedListener?) : BaseViewHolder<TrackModel>(view), View.OnClickListener {
    @BindView(R.id.artist_name)
    lateinit var _textViewArtistName: TextView

    @BindView(R.id.song_name)
    lateinit var _textViewSongName: TextView

    @BindView(R.id.album_cover_photo)
    lateinit var _imageViewAlbumCoverPhoto: ImageView

    @BindView(R.id.preview_track)
    lateinit var _previewTrackImage: ImageView

    private var _data: TrackModel? = null

    init {
        view.setOnClickListener(this)

    }

    @OnClick(R.id.preview_track)
    fun onPreviewClick() {
        //todo:need to udpate button image to look like a stop button if setting to play
        //todo:also need listner on media player to know when to change button back to play

        //play media
        _selectedListener!!.onPreviewItem(adapterPosition, _data)
    }

    override fun onClick(view: View) {

        _selectedListener!!.onItemSelected(adapterPosition, _data)
    }

    val songViewModel: TrackModel?
        get() = data


    override fun bind(data: TrackModel?) {
        //data = data

        _data = data

        _textViewSongName.text = data!!.trackName
        _textViewArtistName.text = data!!.artistName
        Picasso.with(_imageViewAlbumCoverPhoto.context).load(data.artworkUrl100).into(_imageViewAlbumCoverPhoto)

    }

    companion object {

        fun newInstance(parent: ViewGroup, selectedListener: SongSelectedListener?, layout: Int): SongViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
            return SongViewHolder(view, selectedListener)
        }
    }
}

