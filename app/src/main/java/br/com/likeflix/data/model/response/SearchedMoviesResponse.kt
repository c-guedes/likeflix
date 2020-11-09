package br.com.likeflix.data.model.response

data class SearchedMoviesResponse(
  val page: Long,
  val total_results: Long,
  val total_pages: Long,
  val results: List<MovieResponse>
)

data class MovieResponse(
  val popularity: Double,
  val vote_count: Long,
  val video: Boolean,
  val poster_path: String,
  val id: Long,
  val adult: Boolean,
  val backdrop_path: String? = null,
  val original_language: String,
  val original_title: String,
  val genre_ids: List<Long>,
  val title: String,
  val vote_average: Double,
  val overview: String,
  val release_date: String
)