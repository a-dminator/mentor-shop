package io.adev.aaa_mentor_shop

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tomato = Product(title = "Помидор", price = 42.99)
        val cucumber = Product(title = "Огурец", price = 56.99)
        val potato = Product(title = "Картошка", price = 21.99)

        val vegetables = listOf(tomato, cucumber, potato)

        recyclerView {
            layoutManager = LinearLayoutManager(this@ProductsActivity)
            adapter = ProductsAdapter(products = vegetables, context = this@ProductsActivity)
        }
    }

}

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
    }
}

// ProductView является FrameLayout. Для его создания необходим
// Context, который для Android
class ProductView(context: Context) : FrameLayout(context) {
    lateinit var titleView: TextView // lateinit означает, что переменной
    lateinit var priceView: TextView // не нужно задавать начальное значение
    init { // код, который выполнится при создании каждого объекта ProductView

        // Задаём параметры макета для ProductView.
        // То же самое, что и lparams
        layoutParams = LayoutParams(matchParent, wrapContent)

        // Описание интерфейса ячейки
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


class Product(val title: String, val price: Double)

class ProductViewHolder(val view: ProductView) : RecyclerView.ViewHolder(view)