package net.mobiquity.core.platform

import net.mobiquity.core.data.local.LocalDataSource
import net.mobiquity.core.data.remote.RemoteDataSource
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    override val remoteDataSource: RemoteDataSource,
    override val localDataSource: LocalDataSource
) : BaseRepositoryInterface