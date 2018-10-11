package io.adev.mentor_shop.view

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.Toolbar
import org.jetbrains.anko.*
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.drawerLayout

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (Preferences.currentCategoryUrl != null) {
//            startActivity<ProductsActivity>("url" to Preferences.currentCategoryUrl)
//        }

//        frameLayout {
//
//            imageView {
//                imageResource = R.drawable.my_favorite_image
//            }.lparams {
//                width = matchParent
//                height = matchParent
//            }
//
//            verticalLayout {
//
//                toolbar = toolbar { // вёрстка замены ActionBar
//                    title = "no title"
//                    backgroundColor = Color.YELLOW
//                    button {
//                        text = "123"
//                    }
//                }.lparams {
//                    width = matchParent
//                    height = dip(56)
//                }
//
//                button {
//                    text = "Войти"
//                    textSize = 27f
//                    typeface = Typeface.createFromAsset(assets, "intro-inline.otf")
//                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//                    onClick {
//                        startActivity<CategoriesActivity>()
//                    }
//                }.lparams {
//                    width = matchParent
//                    bottomMargin = dip(25)
//                    marginStart = dip(10)
//                }
//            }
//        }

        drawerLayout {
            verticalLayout {
                toolbar = toolbar {

                }.lparams {
                    width = matchParent
                    height = dip(56)
                }
                button {
                    onClick {
                        startActivity<CategoriesActivity>()
                    }
                }
            }
            navigationView {
                backgroundColor = Color.BLUE
                recyclerView {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }.lparams {
                gravity = Gravity.START
                height = matchParent
            }
            openDrawer(Gravity.START)
        }

        setActionBar(toolbar) // замена ActionBar
        actionBar!!.setHomeButtonEnabled(true) // TODO доделать
    }
}
