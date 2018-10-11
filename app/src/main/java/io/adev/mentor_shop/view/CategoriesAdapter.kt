package io.adev.mentor_shop.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import io.adev.mentor_shop.data.Preferences
import io.adev.mentor_shop.entities.Category
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.UI
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textView

class CategoriesAdapter(
    val context: Context
) : RecyclerView.Adapter<CategoryViewHolder>() {

    var categories: List<Category> = emptyList()
    fun update(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun getItemCount() = categories.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = run {

        val ui = context.UI {
            textView {
                textSize = 22f
            }
        }

        CategoryViewHolder(ui.view as TextView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.view.text = category.title
        holder.view.onClick {
            Preferences.currentCategoryUrl = category.url
            val categoryJson = JSON.stringify(category)
            context.startActivity<ProductsActivity>("category" to categoryJson)
        }
    }

}