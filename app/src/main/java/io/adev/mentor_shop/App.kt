package io.adev.mentor_shop

import android.app.Application
import android.arch.persistence.room.Room
import com.chibatching.kotpref.Kotpref

lateinit var db: AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).build()
    }

}