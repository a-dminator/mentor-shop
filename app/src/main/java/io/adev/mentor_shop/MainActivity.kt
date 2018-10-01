package io.adev.mentor_shop

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

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

                customView<HeaderView> {
                    titleView.text = "Главная"
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
    }
}
