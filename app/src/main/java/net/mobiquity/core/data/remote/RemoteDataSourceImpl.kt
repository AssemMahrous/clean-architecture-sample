package net.mobiquity.core.data.remote

import net.mobiquity.core.data.remote.requests.ApiRequests
import retrofit2.Retrofit

class RemoteDataSourceImpl(retrofit: Retrofit) :
    RemoteDataSource {
    override val apiRequests: ApiRequests = retrofit.create(ApiRequests::class.java)
}
