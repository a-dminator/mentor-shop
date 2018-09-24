package io.adev.mentor_shop

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout {

            verticalLayout {

                textView {
                    text = "padding"
                    padding = dip(20)
                    backgroundColor = Color.RED
                }.lparams {}

                textView {
                    text = "margin"
                    backgroundColor = Color.BLUE
                }.lparams {
                    margin = dip(20)
                }
            }

        }
    }

}
