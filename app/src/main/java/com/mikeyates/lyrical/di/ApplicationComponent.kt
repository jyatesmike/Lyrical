package com.mikeyates.lyrical.di

import android.content.Context
import com.mikeyates.lyrical.network.LyricsService
import com.mikeyates.lyrical.network.iTunesService
import dagger.Component
import javax.inject.Singleton

/**
 * Created by jamesyates on 5/29/17.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    @AppContext
    fun appContext(): Context?

    fun getiTunesService(): iTunesService
    val lyricsService: LyricsService
}
