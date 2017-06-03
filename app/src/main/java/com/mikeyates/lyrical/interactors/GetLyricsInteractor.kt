package com.mikeyates.lyrical.interactors

import com.mikeyates.lyrical.exception.SearchLyricsFailureException
import com.mikeyates.lyrical.models.LyricsModel
import com.mikeyates.lyrical.network.LyricsService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jamesyates on 5/29/17.
 */

class GetLyricsInteractor(private val service: LyricsService) {

    fun execute(artist: String, song: String): Flowable<LyricsModel> {
        return service.getSearchResults("getSong", artist, song, "xml")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ this.handleSearchError(it) })
                .toFlowable()
    }

    private fun handleSearchError(throwable: Throwable) {
        throw SearchLyricsFailureException(throwable)
    }
}
