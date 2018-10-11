package io.adev.mentor_shop

import io.adev.mentor_shop.data.OkHttpRequestMaker
import io.adev.mentor_shop.data.RequestMaker
import io.adev.mentor_shop.presentation.CategoriesPresenter
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val di = Kodein {
    bind<RequestMaker>() with singleton { OkHttpRequestMaker() }
    bind<CategoriesPresenter>() with singleton { CategoriesPresenter(instance()) }
}

fun getCategoriesPresenter() = di.direct.instance<CategoriesPresenter>()