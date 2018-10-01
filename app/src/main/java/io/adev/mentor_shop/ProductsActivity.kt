package io.adev.mentor_shop

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.kodein.di.direct
import org.kodein.di.generic.instance

class ProductsActivity : AppCompatActivity() {

    val requestMaker: RequestMaker = di.direct.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {

            frameLayout {
                progressBar {

                }.lparams {
                    gravity = Gravity.CENTER
                }
            }

            val categoryJson = intent.getStringExtra("category")
            val category: Category = JSON.parse(categoryJson)

            val json = async(Dispatchers.IO) {
                requestMaker.make(category.url)
            }.await()

            val vegetables: ProductsList = JSON.parse(json)

            verticalLayout {
                customView<HeaderView> {
                    titleView.text = category.title
                }
                recyclerView {
                    layoutManager = GridLayoutManager(this@ProductsActivity, 2)
                    adapter = ProductsAdapter(products = vegetables.products, context = this@ProductsActivity)
                }
            }
        }
    }

}

@Serializable
class Product(
    val title: String,
    val price: Double,
    val imageUrl: String
)

@Serializable
class ProductsList(
    val products: List<Product>
)

class ProductsAdapter(
    val products: List<Product>,
    val context: Context
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(recyclerView: ViewGroup, viewType: Int) = run {

        // Чтобы создать ProductView, нам нужен Context
        val itemView = ProductView(context)

        // Теперь в ячейке лежит ProductView
        ProductViewHolder(view = itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        // holder.view теперь имеет тип ProductView
        // У ProductView есть 2 константы: titleView и priceView.
        // Они имеют тип TextView. Задаём этим TextView новый text
        holder.view.titleView.text = product.title

        // toString() - функция, превращающая дробное число в текст
        holder.view.priceView.text = product.price.toString()

        holder.view.onClick {
            val json = JSON.stringify(product)
            context.startActivity<ProductDetailsActivity>(
                "product" to json
            )
        }

        // загрузка картинки
        Picasso.get().load(product.imageUrl).into(holder.view.pictureView)
    }
}

// ProductView является FrameLayout. Для его создания необходим
// Context, который для Android
class ProductView(context: Context) : FrameLayout(context) {
    lateinit var titleView: TextView // lateinit означает, что переменной
    lateinit var priceView: TextView // не нужно задавать начальное значение
    lateinit var pictureView: ImageView

    init { // код, который выполнится при создании каждого объекта ProductView

        // Задаём параметры макета для ProductView.
        // То же самое, что и lparams
        layoutParams = LayoutParams(matchParent, wrapContent)

        // Описание интерфейса ячейки
        frameLayout {
            cardView {
                radius = 30f
                elevation = 7.0f
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    pictureView = imageView {

                    }.lparams {
                        width = matchParent
                        height = dip(200)
                    }
                    verticalLayout {
                        titleView = textView {

                        }.lparams {
                            gravity = Gravity.CENTER
                        }
                        priceView = textView {

                        }.lparams {
                            gravity = Gravity.CENTER
                        }
                    }
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }
            }.lparams {
                margin = dip(15)
                width = matchParent
                height = wrapContent
            }
        }
    }
}

class ProductViewHolder(val view: ProductView) : RecyclerView.ViewHolder(view)














