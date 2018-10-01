package io.adev.mentor_shop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.nestedScrollView

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = intent.getStringExtra("product")
        val product: Product = JSON.parse(json)

        nestedScrollView {
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
                    text = "123"
                    textSize = 26f
                }
                button {
                    text = "Купить"
                }
                button {
                    text = "Поделиться"
                    onClick {
                        val intent = Intent()
                            .setData(Uri.parse("http://twitter.com/share?text=${product.title}"))
                            .setAction(Intent.ACTION_VIEW)
                        startActivity(intent)
                    }
                }
            }
        }
    }

}
