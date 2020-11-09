package br.com.likeflix.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchedMoviesBO(
    val page: Long,
    val total_results: Long,
    val total_pages: Long,
    val results: List<MovieBO>
): Parcelable

@Parcelize
data class MovieBO(
    val popularity: Double? = null,
    val vote_count: Long? = null,
    val video: Boolean? = null,
    val poster_path: String,
    val id: Long,
    val adult: Boolean,
    val backdrop_path: String? = null,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Long>,
    val title: String,
    val vote_average: Double? = null,
    val overview: String,
    val release_date: String
): Parcelable