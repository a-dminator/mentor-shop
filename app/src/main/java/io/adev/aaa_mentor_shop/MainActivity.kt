package io.adev.aaa_mentor_shop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout {

            verticalLayout {

                editText {
                    hint = "Login"
                }.lparams {
                    height = dip(40)
                    margin = dip(25)
                }

                button {
                    text = "Просто привет."
                }.lparams {
                    height = dip(40)
                    margin = dip(25)
                }
            }.lparams {
                gravity = Gravity.CENTER
            }

            textView {
                text = "32"
            }
        }
    }
}
