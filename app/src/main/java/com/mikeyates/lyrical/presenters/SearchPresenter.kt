package com.mikeyates.lyrical.presenters

import com.mikeyates.lyrical.interactors.GetTracksInteractor
import com.mikeyates.lyrical.views.SearchView
import com.mikeyates.lyrical.network.iTunesSearchResponse

import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by jamesyates on 5/29/17.
 */

class SearchPresenter(private val view: SearchView, private val interactor: GetTracksInteractor) {

    fun refresh(searchTerms: String) {
        view.showProgressDialog()
        interactor.execute(searchTerms).subscribe(SearchObserver())
    }

    private inner class SearchObserver : DisposableSubscriber<iTunesSearchResponse>() {

        override fun onNext(o: iTunesSearchResponse) {
            view.hideProgressDialog()
            view.populateList(o)
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
