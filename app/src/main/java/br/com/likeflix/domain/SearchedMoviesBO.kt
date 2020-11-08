package br.com.likeflix.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchedMoviesBO(
    val page: Double,
    val total_results: Double,
    val total_pages: Double,
    val results: List<MovieBO>
): Parcelable

@Parcelize
data class MovieBO(
    val popularity: Double? = null,
    val vote_count: Double? = null,
    val video: Boolean? = null,
    val poster_path: String,
    val id: Double,
    val adult: Boolean,
    val backdrop_path: String? = null,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Double>,
    val title: String,
    val vote_average: Double? = null,
    val overview: String,
    val release_date: String
): Parcelable