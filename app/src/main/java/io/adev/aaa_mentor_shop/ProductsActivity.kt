package io.adev.aaa_mentor_shop

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val json = """
{
    "products": [
        {
            "title": "Помидор",
            "price": 42.99,
            "imageUrl": "https://greenmylife-wpengine.netdna-ssl.com/wp-content/uploads/2014/06/HighYield-Tomato.jpg"
        },
        {
            "title": "Огурец",
            "price": 56.99,
            "imageUrl": "http://demandware.edgesuite.net/sits_pod32/dw/image/v2/BBBW_PRD/on/demandware.static/-/Sites-jss-master/default/dwdc19b9f9/images/products/vegetables/0047g_01_katrina.jpg?sw=387&cx=216&cy=0&cw=1196&ch=1196"
        },
        {
            "title": "Картошка",
            "price": 21.99,
            "imageUrl": "https://cdn.mr-fothergills.co.uk/product-images/op/z/POT-270z.jpg"
        }
    ]
}
        """

        val vegetables: ProductsList = JSON.parse(json)

        recyclerView {
            layoutManager = LinearLayoutManager(this@ProductsActivity)
            adapter = ProductsAdapter(products = vegetables.products, context = this@ProductsActivity)
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
        linearLayout {
            orientation = LinearLayout.VERTICAL
            pictureView = imageView {

            }.lparams {
                width = matchParent
                height = dip(200)
            }
            frameLayout {
                titleView = textView {

                }
                priceView = textView {

                }.lparams {
                    gravity = Gravity.END
                }
            }
        }
    }
}

class ProductViewHolder(val view: ProductView) : RecyclerView.ViewHolder(view)