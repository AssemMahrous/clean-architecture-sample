package net.mobiquity.modules.category

import net.mobiquity.core.utils.Event
import net.mobiquity.modules.category.domain.Category
import net.mobiquity.modules.category.entities.CategoriesResponse
import net.mobiquity.modules.category.entities.CategoryView

object Mapper {
    fun mapToCategory(categoriesResponse: CategoriesResponse): Category = Category(
        categoriesResponse.description,
        categoriesResponse.id,
        categoriesResponse.name
    )

    fun mapToCategoriesView(list: List<Category>): Event<List<CategoryView>> {
        return Event(list.map { mapToCategoryView(it) })
    }

    private fun mapToCategoryView(category: Category): CategoryView =
        CategoryView(category.id, category.name)
}