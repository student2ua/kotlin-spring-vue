package com.tor.kotlin.spring.backend.jaas

class UserAlreadyExistException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, p1: Throwable?) : super(message, p1)
    constructor(p0: Throwable?) : super(p0)

    companion object {

        private val serialVersionUID = 5861310537366287163L
    }

}