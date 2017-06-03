package com.mikeyates.lyrical.di

import com.mikeyates.lyrical.network.LyricsService
import com.mikeyates.lyrical.ui.lyrics.LyricsFragment
import dagger.Component

/**
 * Created by jamesyates on 5/29/17.
 */

@ViewScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(LyricsModule::class))
interface LyricsComponent {
    fun inject(fragment: LyricsFragment)
    val lyricsService: LyricsService
}
