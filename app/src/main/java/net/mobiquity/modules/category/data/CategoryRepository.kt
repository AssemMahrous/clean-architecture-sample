package net.mobiquity.modules.category.data

import io.reactivex.Single
import net.mobiquity.core.data.local.LocalDataSource
import net.mobiquity.core.data.remote.RemoteDataSource
import net.mobiquity.core.platform.BaseRepository
import net.mobiquity.modules.category.Mapper
import net.mobiquity.modules.category.domain.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : BaseRepository(remoteDataSource, localDataSource),
    CategoryRepositoryInterface {
    override fun getCategories(): Single<List<Category>> {
        return remoteDataSource.apiRequests.getCategories()
            .doOnSuccess(localDataSource::saveProducts)
            .map { list -> list.map(Mapper::mapToCategory) }
    }
}