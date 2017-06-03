package com.mikeyates.lyrical.di

import com.mikeyates.lyrical.ui.search.SearchFragment
import com.mikeyates.lyrical.network.iTunesService

import dagger.Component

/**
 * Created by jamesyates on 5/29/17.
 */

@ViewScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(SearchModule::class))
interface SearchComponent {
    fun inject(fragment: SearchFragment)
    fun getiTunesService(): iTunesService
}
