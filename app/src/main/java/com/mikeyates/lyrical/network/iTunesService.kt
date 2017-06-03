package com.mikeyates.lyrical.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jamesyates on 5/29/17.
 */

interface iTunesService {
    @GET("/search")
    fun getSearchResults(@Query("term") term: String): Single<iTunesSearchResponse>
}
