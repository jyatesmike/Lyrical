package com.mikeyates.lyrical.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by jamesyates on 5/29/17.
 */

@Root(name = "LyricsResult", strict = false)
class LyricsModel {
    //@Element(name = "artist", required = false)
    @get:Element(name = "artist", required = false)
    @set:Element(name = "artist", required = false)
    public var artist: String? = null

    //@Element(name = "song", required = false)
    @get:Element(name = "song", required = false)
    @set:Element(name = "song", required = false)
    public var song: String? = null

    //@Element(name = "lyrics", required = false)
    @get:Element(name = "lyrics", required = false)
    @set:Element(name = "lyrics", required = false)
    public var  lyrics: String? = null

    //@Element(name = "url", required = false)
    @get:Element(name = "url", required = false)
    @set:Element(name = "url", required = false)
    public var  url: String? = null

    //@Element(name = "page_namespace", required = false)
    private val page_namespace: String? = null
    //@Element(name = "page_id", required = false)
    private val page_id: String? = null
    //@Element(name = "isOnTakedownList", required = false)
    private val isOnTakedownList: String? = null
}
