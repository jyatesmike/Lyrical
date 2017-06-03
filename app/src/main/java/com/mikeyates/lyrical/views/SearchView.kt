package com.mikeyates.lyrical.views

import com.mikeyates.lyrical.network.iTunesSearchResponse

/**
 * Created by jamesyates on 5/29/17.
 */

interface SearchView {
    fun populateList(data: iTunesSearchResponse)
    fun showProgressDialog()
    fun hideProgressDialog()
    fun showMessage(message: String)
}
