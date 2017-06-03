package com.mikeyates.lyrical.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Scope

/**
 * Created by jamesyates on 5/29/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ViewScope
