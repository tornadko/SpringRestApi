package com.alex.develop.SpringRestApiExample.dal

import java.util.concurrent.atomic.AtomicLong


class User {

    val id: Long
    val name: String?

    companion object {
        private val COUNTER = AtomicLong()
    }

    constructor(id: Long, name: String?) {
        this.id = id
        this.name = name
    }

    constructor(name: String) : this(COUNTER.getAndIncrement(), name)

    constructor() : this(COUNTER.getAndIncrement(), null)
}
