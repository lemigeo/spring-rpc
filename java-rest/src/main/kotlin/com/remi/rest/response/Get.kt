package com.remi.rest.response

class Get<T>: RestResponse {

    constructor(): super()
    constructor(errorMessage: String): super(errorMessage)

    var data: T? = null
    constructor(t: T) {
        data = t
    }
}