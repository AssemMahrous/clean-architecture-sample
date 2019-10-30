package net.mobiquity.core.utils

import net.mobiquity.BuildConfig

fun isDevelopmentDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG && BuildConfig.FLAVOR.contains("develop", ignoreCase = true)) {
        block.invoke()
    }
}