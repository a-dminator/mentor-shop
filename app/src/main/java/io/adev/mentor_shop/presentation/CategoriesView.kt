package io.adev.mentor_shop.presentation

import io.adev.mentor_shop.entities.Category

interface CategoriesView {
    fun displayCategories(categories: List<Category>)
    fun displayLoading()
}