package com.mikeyates.lyrical.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Qualifier

/**
 * Created by jamesyates on 5/29/17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class AppContext
