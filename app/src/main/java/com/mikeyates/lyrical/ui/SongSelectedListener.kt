package com.mikeyates.lyrical.ui

import com.mikeyates.lyrical.models.TrackModel

/**
 * Created by jamesyates on 5/29/17.
 */

interface SongSelectedListener {
    fun onItemSelected(position: Int, v: TrackModel?)
    fun onPreviewItem(position: Int, v: TrackModel?)
}