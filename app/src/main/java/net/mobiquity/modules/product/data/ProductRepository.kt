package net.mobiquity.modules.product.data

import io.reactivex.Single
import net.mobiquity.core.data.local.LocalDataSource
import net.mobiquity.core.data.remote.RemoteDataSource
import net.mobiquity.core.platform.BaseRepository
import net.mobiquity.modules.product.domain.Product
import javax.inject.Inject


class ProductRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : BaseRepository(remoteDataSource, localDataSource),
    ProductRepositoryInterface {
    override fun getProductsByCategoryId(categoryId: String): Single<List<Product>> {
        return Single.just(localDataSource.getProductsByCategoryId(categoryId))
    }
}