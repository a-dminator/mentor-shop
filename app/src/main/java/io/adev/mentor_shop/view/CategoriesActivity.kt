package io.adev.mentor_shop.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import io.adev.mentor_shop.di
import io.adev.mentor_shop.entities.Category
import io.adev.mentor_shop.presentation.CategoriesPresenter
import io.adev.mentor_shop.presentation.CategoriesView
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.progressBar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout
import org.kodein.di.direct
import org.kodein.di.generic.instance

class CategoriesActivity : CategoriesView, AppCompatActivity() {

    private val presenter: CategoriesPresenter = di.direct.instance()

    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var loaderView: ProgressBar
    private lateinit var contentView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesAdapter = CategoriesAdapter(this)

        frameLayout {

            loaderView = progressBar {
                visibility = View.GONE
            }.lparams {
                gravity = Gravity.CENTER
            }

            contentView = verticalLayout {
                visibility = View.GONE
                recyclerView {
                    layoutManager = LinearLayoutManager(this@CategoriesActivity)
                    adapter = categoriesAdapter
                }
            }.lparams {
                width = matchParent
                height = matchParent
            }
        }

        presenter.onCreateView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.onAppear()
    }

    override fun onPause() {
        super.onPause()
        presenter.onDisappear()
    }

    override fun displayLoading() {
        loaderView.visibility = View.VISIBLE
        contentView.visibility = View.GONE
    }

    override fun displayCategories(categories: List<Category>) {
        loaderView.visibility = View.GONE
        contentView.visibility = View.VISIBLE
        categoriesAdapter.update(categories)
    }

}
















