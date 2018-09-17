package io.adev.aaa_mentor_shop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
                bottomMargin = dip(25)
                marginStart = dip(10)
            }
        }
    }

}
