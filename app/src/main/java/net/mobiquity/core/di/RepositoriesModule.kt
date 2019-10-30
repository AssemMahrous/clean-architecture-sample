/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.mobiquity.core.di

import dagger.Binds
import dagger.Module
import net.mobiquity.modules.category.data.CategoryRepository
import net.mobiquity.modules.category.data.CategoryRepositoryInterface
import net.mobiquity.modules.product.data.ProductRepository
import net.mobiquity.modules.product.data.ProductRepositoryInterface
import javax.inject.Singleton


@Suppress("unused")
@Module
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindCategoryRepository(categoryRepository: CategoryRepository): CategoryRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindProductRepository(productRepository: ProductRepository): ProductRepositoryInterface
}
