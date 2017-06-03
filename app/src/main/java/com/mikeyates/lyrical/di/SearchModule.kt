package com.mikeyates.lyrical.di

import com.mikeyates.lyrical.interactors.GetTracksInteractor
import com.mikeyates.lyrical.presenters.SearchPresenter
import com.mikeyates.lyrical.views.SearchView
import com.mikeyates.lyrical.network.iTunesService

import dagger.Module
import dagger.Provides

/**
 * Created by jamesyates on 5/29/17.
 */

@Module
class SearchModule(private val view: SearchView) {

    @Provides
    fun provideInteractor(service: iTunesService): GetTracksInteractor {
        return GetTracksInteractor(service)
    }

    @Provides
    fun providePresenter(interactor: GetTracksInteractor): SearchPresenter {
        return SearchPresenter(view, interactor)
    }

}
