package io.adev.mentor_shop

import android.content.Context
import android.graphics.Typeface
import android.widget.FrameLayout
import android.widget.TextView
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView

class HeaderView(context: Context) : FrameLayout(context) {
    lateinit var titleView: TextView

    init {
        titleView = textView {
            typeface = Typeface.createFromAsset(context.assets, "intro-inline.otf")
            textSize = 23f
            padding = dip(15)
        }
    }

}