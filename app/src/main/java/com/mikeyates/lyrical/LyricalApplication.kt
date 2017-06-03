package com.mikeyates.lyrical

import android.app.Application
import android.content.Context

import com.mikeyates.lyrical.di.ApplicationComponent
import com.mikeyates.lyrical.di.ApplicationModule
import com.mikeyates.lyrical.di.DaggerApplicationComponent

/**
 * Created by jamesyates on 5/29/17.
 */

class LyricalApplication : Application() {

    var component: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    companion object {

        operator fun get(context: Context?): LyricalApplication {
            return context!!.applicationContext as LyricalApplication
        }
    }
}
