package com.mikeyates.lyrical.exception

/**
 * Created by jamesyates on 5/29/17.
 */

class SearchTracksFailureException : IllegalArgumentException {
    constructor() : super() {}

    constructor(cause: Throwable) : super(cause) {}
}
