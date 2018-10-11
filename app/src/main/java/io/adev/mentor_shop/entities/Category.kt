package io.adev.mentor_shop.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "categories")
@Serializable
data class Category(
    @PrimaryKey val id: Int,
    val title: String,
    val url: String
)