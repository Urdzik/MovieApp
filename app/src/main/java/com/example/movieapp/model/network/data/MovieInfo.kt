package com.example.movieapp.model.network.data

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json


data class MovieInfo (
    @Json(name = "adult") val adult: Boolean,
                          val backdrop_path: String,
    @Json(name = "belongs_to_collection") val belongsToCollection: String,
    @Json(name = "budget") val budget: Int,
    @Json(name = "genres") val genres: List<Genres>,
    @Json(name = "homepage") val homepage: String,
    @Json(name = "id") val id: Int,
    @Json(name = "imdb_id") val imdbId: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Double,
                               val poster_path: String,
    @Json(name = "production_companies") val productionCompanies: List<ProductionCompanies>,
    @Json(name = "production_countries") val productionCountries: List<ProductionCountries>,
    @Json(name = "release_date") val release_date: String,
    @Json(name = "revenue") val revenue: Int,
    @Json(name = "runtime") val runtime: Int,
    @Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguages>,
    @Json(name = "status") val status: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
)

data class SpokenLanguages (
    @SerializedName("iso_639_1") val iso_639_1 : String,
    @SerializedName("name") val name : String
)
data class ProductionCountries (
    @SerializedName("iso_3166_1") val iso_3166_1 : String,
    @SerializedName("name") val name : String
)
data class ProductionCompanies (
    @SerializedName("id") val id : Int,
    @SerializedName("logo_path") val logoPath : String,
    @SerializedName("name") val name : String,
    @SerializedName("origin_country") val originCountry : String
)

data class Genres (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)