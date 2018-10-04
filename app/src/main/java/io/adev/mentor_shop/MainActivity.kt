package io.adev.mentor_shop

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (Preferences.currentCategoryUrl != null) {
//            startActivity<ProductsActivity>("url" to Preferences.currentCategoryUrl)
//        }

        frameLayout {

            imageView {
                imageResource = R.drawable.my_favorite_image
            }.lparams {
                width = matchParent
                height = matchParent
            }

            verticalLayout {

                toolbar = toolbar { // вёрстка замены ActionBar
                    title = "no title"
                    backgroundColor = Color.YELLOW
                    button {
                        text = "123"
                    }
                }.lparams {
                    width = matchParent
                    height = dip(56)
                }

                button {
                    text = "Войти"
                    textSize = 27f
                    typeface = Typeface.createFromAsset(assets, "intro-inline.otf")
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    onClick {
                        startActivity<CategoriesActivity>()
                    }
                }.lparams {
                    width = matchParent
                    bottomMargin = dip(25)
                    marginStart = dip(10)
                }
            }
        }

        setSupportActionBar(toolbar) // замена ActionBar
    }
}
