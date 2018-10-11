package io.adev.mentor_shop

import android.app.Application
import android.arch.persistence.room.Room
import com.chibatching.kotpref.Kotpref
import io.adev.mentor_shop.data.ShopDatabase

lateinit var db: ShopDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)

        db = Room.databaseBuilder(
            this,
            ShopDatabase::class.java,
            "shop"
        ).build()
    }

}