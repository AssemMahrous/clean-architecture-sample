package net.mobiquity.core.platform

import net.mobiquity.core.data.local.LocalDataSource
import net.mobiquity.core.data.remote.RemoteDataSource

interface BaseRepositoryInterface {
    val remoteDataSource: RemoteDataSource
    val localDataSource: LocalDataSource
}