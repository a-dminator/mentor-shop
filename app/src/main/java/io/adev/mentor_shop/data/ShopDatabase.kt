package io.adev.mentor_shop.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.adev.mentor_shop.entities.Category

@Database(entities = [Category::class], version = 1) // подробнее в google: android room migrations
abstract class ShopDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
}