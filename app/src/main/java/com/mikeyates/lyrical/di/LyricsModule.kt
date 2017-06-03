package com.mikeyates.lyrical.di

import com.mikeyates.lyrical.interactors.GetLyricsInteractor
import com.mikeyates.lyrical.network.LyricsService
import com.mikeyates.lyrical.presenters.LyricsPresenter
import com.mikeyates.lyrical.views.LyricsView

import dagger.Module
import dagger.Provides

/**
 * Created by jamesyates on 5/29/17.
 */

@Module
class LyricsModule(private val view: LyricsView) {

    @Provides
    fun provideInteractor(service: LyricsService): GetLyricsInteractor {
        return GetLyricsInteractor(service)
    }

    @Provides
    fun providePresenter(interactor: GetLyricsInteractor): LyricsPresenter {
        return LyricsPresenter(view, interactor)
    }

}
