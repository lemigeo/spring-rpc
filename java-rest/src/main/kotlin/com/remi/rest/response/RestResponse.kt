package com.remi.rest.response

open class RestResponse {
    protected var success: Boolean
    protected var errorMessage: String? = null

    constructor() {
        this.success = true
    }

    constructor(errorMessage: String) {
        this.success = false
        this.errorMessage = errorMessage
    }
}