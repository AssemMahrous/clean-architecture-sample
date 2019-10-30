package net.mobiquity.core.utils

import timber.log.Timber

class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // todo log to fabric or any other logging server
    }
}