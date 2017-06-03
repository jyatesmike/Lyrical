package com.mikeyates.lyrical.exception

/**
 * Created by jamesyates on 5/29/17.
 */

class SearchLyricsFailureException : IllegalArgumentException {
    constructor() : super() {}

    constructor(cause: Throwable) : super(cause) {}
}
