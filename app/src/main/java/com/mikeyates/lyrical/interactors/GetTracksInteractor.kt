package com.mikeyates.lyrical.interactors

import com.mikeyates.lyrical.exception.SearchTracksFailureException
import com.mikeyates.lyrical.network.iTunesSearchResponse
import com.mikeyates.lyrical.network.iTunesService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by jamesyates on 5/29/17.
 */

class GetTracksInteractor(private val service: iTunesService) {

    fun execute(searchTerms: String): Flowable<iTunesSearchResponse> {
        return service.getSearchResults(searchTerms)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ this.handleSearchError(it) })
                .toFlowable()
    }

    private fun handleSearchError(throwable: Throwable) {
        throw SearchTracksFailureException()
    }
}
