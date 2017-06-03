package com.mikeyates.lyrical.models

import com.google.gson.annotations.SerializedName

import java.util.Date

/**
 * Created by jamesyates on 5/29/17.
 */

class TrackModel {
    @SerializedName("wrapperType")
    private val _wrapperType: String? = null
    @SerializedName("kind")
    private val _kind: String? = null
    @SerializedName("artistId")
    private val _artistId: Int = 0
    @SerializedName("collectionId")
    private val _collectionId: Int = 0
    @SerializedName("trackId")
    private val _trackId: Int = 0
    @SerializedName("artistName")
    val artistName: String? = null
    @SerializedName("collectionName")
    private val _collectionName: String? = null
    @SerializedName("trackName")
    val trackName: String? = null
    @SerializedName("collectionCensoredName")
    private val _collectionCensoredName: String? = null
    @SerializedName("trackCensoredName")
    private val _trackCensoredName: String? = null
    @SerializedName("artistViewUrl")
    private val _artistViewUrl: String? = null
    @SerializedName("collectionViewUrl")
    private val _collectionViewUrl: String? = null
    @SerializedName("trackViewUrl")
    private val _trackViewUrl: String? = null
    @SerializedName("previewUrl")
    val previewUrl: String? = null
    @SerializedName("artworkUrl30")
    private val _artworkUrl30: String? = null
    @SerializedName("artworkUrl60")
    val artworkUrl60: String? = null
    @SerializedName("artworkUrl100")
    val artworkUrl100: String? = null
    @SerializedName("collectionPrice")
    private val _collectionPrice: Float = 0.toFloat()
    @SerializedName("trackPrice")
    private val _trackPrice: Float = 0.toFloat()
    @SerializedName("releaseDate")
    private val _releaseDate: Date? = null
    @SerializedName("collectionExplicitness")
    private val _collectionExplicitness: String? = null
    @SerializedName("trackExplicitness")
    private val _trackExplicitness: String? = null
    @SerializedName("discCount")
    private val _discCount: Int = 0
    @SerializedName("discNumber")
    private val _discNumber: Int = 0
    @SerializedName("trackCount")
    private val _trackCount: Int = 0
    @SerializedName("trackNumber")
    private val _trackNumber: Int = 0
    @SerializedName("trackTimeMillis")
    private val _trackTimeMillis: Int = 0
    @SerializedName("country")
    private val _country: String? = null
    @SerializedName("currency")
    private val _currency: String? = null
    @SerializedName("primaryGenreName")
    private val _primaryGenreName: String? = null
    @SerializedName("isStreamable")
    private val _isStreamable: Boolean = false
}
