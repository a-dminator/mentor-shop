package io.adev.aaa_mentor_shop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.UI
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.textView

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tomato = Product(title = "Помидор")
        val cucumber = Product(title = "Огурец")
        val potato = Product(title = "Картошка")

        val products = listOf(tomato, cucumber, potato)

        recyclerView {
            layoutManager = LinearLayoutManager(this@ProductsActivity)
            adapter = object : RecyclerView.Adapter<ProductViewHolder>() {

                override fun getItemCount() = products.size

                override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = run {
                    val itemUI = UI {
                        textView {
                            text = "My text"
                        }
                    }
                    ProductViewHolder(view = itemUI.view as TextView)
                }

                override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
                    val product = products[position]
                    holder.view.text = product.title
                }

            }
        }
    }

}

class Product(val title: String)

class ProductViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)