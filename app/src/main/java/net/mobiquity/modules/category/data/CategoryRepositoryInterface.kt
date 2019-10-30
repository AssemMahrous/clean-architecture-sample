package net.mobiquity.modules.category.data

import io.reactivex.Single
import net.mobiquity.modules.category.domain.Category

interface CategoryRepositoryInterface {
    fun getCategories(): Single<List<Category>>
}