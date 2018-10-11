package io.adev.mentor_shop.data

import com.chibatching.kotpref.KotprefModel

object Preferences : KotprefModel() {
    var currentCategoryUrl by nullableStringPref(default = null)
}