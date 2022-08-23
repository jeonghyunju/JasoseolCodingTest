package com.example.jasoseolcodingtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @field:SerializedName("lastBuildDate") val lastBuildDate: String,
    @field:SerializedName("total") val total: Int,
    @field:SerializedName("start") val start: Int,
    @field:SerializedName("display") val display: Int,
    @field:SerializedName("items") val items: List<MovieItem>
)

@Entity(tableName = "favorite")
data class MovieItem(
    @PrimaryKey val idx: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("link") val link: String,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("subtitle") val subTitle: String,
    @field:SerializedName("director") val director: String,
    @field:SerializedName("actor") val actor: String,
    @field:SerializedName("userRating") val userRating: Double,
    var isFavorite: Boolean = false,
): Serializable