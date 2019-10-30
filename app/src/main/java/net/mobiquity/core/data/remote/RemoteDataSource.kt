package net.mobiquity.core.data.remote

import net.mobiquity.core.data.remote.requests.ApiRequests

interface RemoteDataSource {
    val apiRequests: ApiRequests
}
