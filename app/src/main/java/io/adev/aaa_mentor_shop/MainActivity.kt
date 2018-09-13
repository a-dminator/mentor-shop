package io.adev.aaa_mentor_shop

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout {
            button {
                text = "Войти"
                onClick {
                    startActivity<ProductsActivity>()
                }
            }.lparams {
                width = matchParent
            }
        }
    }

}
