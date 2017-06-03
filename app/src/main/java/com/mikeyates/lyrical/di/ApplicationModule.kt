package com.mikeyates.lyrical.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Preconditions`.checkNotNull
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mikeyates.lyrical.LyricalApplication
import com.mikeyates.lyrical.network.LyricsService
import com.mikeyates.lyrical.network.iTunesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by jamesyates on 5/29/17.
 */

@Module
class ApplicationModule(application: LyricalApplication) {

    private val application: LyricalApplication

    init {
        this.application = checkNotNull(application)
    }

    @Provides
    @Singleton
    @AppContext
    fun provideApplication(): LyricalApplication {
        return application
    }

    @Provides
    @Singleton
    @AppContext
    fun provideContext(): Context? {
        return application
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Named(JSONFORMAT_RETROFIT)
    fun provideJsonRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Named(XMLFORMAT_RETROFIT)
    fun provideXmlRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://lyrics.wikia.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
    }

    @Provides
    fun provideiTunesService(@Named(JSONFORMAT_RETROFIT) retrofit: Retrofit): iTunesService {
        return retrofit.create(iTunesService::class.java)
    }

    @Provides
    fun provideLyricsService(@Named(XMLFORMAT_RETROFIT) retrofit: Retrofit): LyricsService {
        return retrofit.create(LyricsService::class.java)
    }

    companion object {
        const val JSONFORMAT_RETROFIT = "JSONFORMAT_RETROFIT"
        const val XMLFORMAT_RETROFIT = "XMLFORMAT_RETROFIT"
    }
}
