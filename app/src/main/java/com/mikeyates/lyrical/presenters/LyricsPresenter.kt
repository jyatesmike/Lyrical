package com.mikeyates.lyrical.presenters

import com.mikeyates.lyrical.interactors.GetLyricsInteractor
import com.mikeyates.lyrical.models.LyricsModel
import com.mikeyates.lyrical.views.LyricsView

import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by jamesyates on 5/29/17.
 */

class LyricsPresenter(private val view: LyricsView, private val interactor: GetLyricsInteractor) {

    fun refresh(artist: String?, song: String?) {
        if (artist!!.length > 0 && song!!.length > 0) {
            view.showProgressDialog()
            interactor.execute(artist, song).subscribe(SearchObserver())
        } else {
            //view.refreshScreen(null)
        }
    }

    private inner class SearchObserver : DisposableSubscriber<LyricsModel>() {

        override fun onNext(o: LyricsModel) {
            view.hideProgressDialog()
            view.refreshScreen(o)
        }

        override fun onError(t: Throwable) {

            view.showMessage("")
        }

        override fun onComplete() {

            view.showMessage("")
            view.hideProgressDialog()
        }
    }
}
