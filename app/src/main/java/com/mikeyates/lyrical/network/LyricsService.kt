package com.mikeyates.lyrical.network

import com.mikeyates.lyrical.models.LyricsModel

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jamesyates on 5/29/17.
 */

interface LyricsService {
    @GET("/api.php")
    fun getSearchResults(@Query("func") func: String, @Query("artist") artist: String, @Query("song") song: String, @Query("fmt") fmt: String): Single<LyricsModel>
}
