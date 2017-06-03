package com.mikeyates.lyrical.views

import com.mikeyates.lyrical.models.LyricsModel

/**
 * Created by jamesyates on 5/29/17.
 */

interface LyricsView {
    fun refreshScreen(data: LyricsModel?)
    fun showProgressDialog()
    fun hideProgressDialog()
    fun showMessage(message: String)
}
