package net.mobiquity.core.data.remote.requests

import io.reactivex.Single
import net.mobiquity.modules.category.entities.CategoriesResponse
import retrofit2.http.GET


interface ApiRequests {
    @GET("/")
    fun getCategories(): Single<List<CategoriesResponse>>
}