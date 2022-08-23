package com.example.jasoseolcodingtest.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("lastBuildDate") val lastBuildDate: String,
    @field:SerializedName("total") val total: Int,
    @field:SerializedName("start") val start: Int,
    @field:SerializedName("display") val display: Int,
    @field:SerializedName("items") val items: List<MovieItem>
)

data class MovieItem(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("link") val link: String,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("subtitle") val subTitle: String,
    @field:SerializedName("director") val director: String,
    @field:SerializedName("actor") val actor: String,
    @field:SerializedName("userRating") val userRating: Double,
    var isFavorite: Boolean = false,
)