package net.mobiquity.modules.category.usecase

import io.reactivex.Single
import net.mobiquity.modules.category.data.CategoryRepositoryInterface
import net.mobiquity.modules.category.domain.Category
import javax.inject.Inject

class GetCategoriesListUseCase @Inject constructor(val repository: CategoryRepositoryInterface) {
    operator fun invoke(): Single<List<Category>> = repository.getCategories()
}