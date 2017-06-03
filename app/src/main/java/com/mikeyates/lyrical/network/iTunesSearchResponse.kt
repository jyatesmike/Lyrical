package com.mikeyates.lyrical.network

import com.google.gson.annotations.SerializedName
import com.mikeyates.lyrical.models.TrackModel

import java.util.ArrayList

/**
 * Created by jamesyates on 5/29/17.
 */

class iTunesSearchResponse {
    @SerializedName("resultCount")
    val count: Int = 0
    @SerializedName("results")
    val results: ArrayList<TrackModel>? = null
}
