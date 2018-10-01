package io.adev.mentor_shop

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.kodein.di.direct
import org.kodein.di.generic.instance

class CategoriesActivity : AppCompatActivity() {

    private val requestMaker: RequestMaker = di.direct.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {

            frameLayout {
                progressBar {

                }.lparams {
                    gravity = Gravity.CENTER
                }
            }

            val json = async(Dispatchers.IO) {
                requestMaker.make("https://api.myjson.com/bins/mubvs")
            }.await()

            val categoriesList: CategoriesList = JSON.parse(json)

            verticalLayout {
                customView<HeaderView> {
                    titleView.text = "Категории"
                }
                recyclerView {
                    layoutManager = LinearLayoutManager(this@CategoriesActivity)
                    adapter = CategoriesAdapter(categoriesList.categories, this@CategoriesActivity)
                }
            }
        }
    }

}

class CategoriesAdapter(
    val categories: List<Category>,
    val context: Context
) : RecyclerView.Adapter<CategoryViewHolder>() {

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

class CategoryViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)

@Serializable
class Category(
    val title: String,
    val url: String
)

@Serializable
class CategoriesList(
    val categories: List<Category>
)