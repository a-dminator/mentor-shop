package io.adev.mentor_shop.presentation

import io.adev.mentor_shop.data.RequestMaker
import io.adev.mentor_shop.db
import io.adev.mentor_shop.entities.CategoriesList
import io.adev.mentor_shop.presentation.base.LifecyclePresenter
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.serialization.json.JSON

class CategoriesPresenter(
    private val requestMaker: RequestMaker
) : LifecyclePresenter<CategoriesView>() {

    override fun onCreateView(view: CategoriesView) {
        super.onCreateView(view)
        view.displayLoading()
    }

    override fun onAppear() {
        super.onAppear()
        loadCategories()
    }

    private fun loadCategories() {

        GlobalScope.launch(Dispatchers.Main) {

            val categories = try {

                val json = async(Dispatchers.IO) {
                    requestMaker.make("https://gist.githubusercontent.com/a-dminator/4e087fe7c82609e0a38352d6f54aac9f/raw/ccd059dd7cee94502766b19cbce11e2c946afa9e/categories.json")
                }.await()

                val categoriesList: CategoriesList = JSON.parse(json)

                async(Dispatchers.IO) {
                    categoriesList.categories.forEach { category ->
                        db.categoriesDao().add(category)
                    }
                }.await()

                categoriesList.categories

            } catch (error: Exception) {

                val categoriesFromDb = async(Dispatchers.IO) {
                    db.categoriesDao().all()
                }.await()

                categoriesFromDb
            }

            view?.displayCategories(categories)
        }

    }

}