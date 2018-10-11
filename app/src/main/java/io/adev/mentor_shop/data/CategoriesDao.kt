package io.adev.mentor_shop.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.adev.mentor_shop.entities.Category

@Dao
interface CategoriesDao {

    @Insert
    fun add(category: Category)

    @Query("SELECT * FROM categories")
    fun all(): List<Category>

    @Query("SELECT * FROM categories WHERE id=:id")
    fun byId(id: Int): Category

    @Query("SELECT * FROM categories WHERE rating >= 3.0 AND productsCount > 0")
    fun notEmptyWithHighRating(): List<Category>

    @Query("SELECT * FROM categories WHERE rating >= 4.0 OR rating < 1.0")
    fun interesting(): List<Category>

}