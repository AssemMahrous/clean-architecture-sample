package net.mobiquity.core.platform

import net.mobiquity.core.data.remote.RemoteDataSource

interface BaseRepositoryInterface {
    fun getRemoteService(): RemoteDataSource
}