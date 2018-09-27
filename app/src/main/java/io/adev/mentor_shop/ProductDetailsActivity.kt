package io.adev.mentor_shop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.button
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = intent.getStringExtra("product")
        val product: Product = JSON.parse(json)

        verticalLayout {
            textView {
                text = product.title
                textSize = 26f
            }
            textView {
                text = product.price.toString()
                textSize = 26f
            }
            textView {
                text = product.description
                textSize = 26f
            }
            button {
                text = "Купить"
            }
        }
    }

}
