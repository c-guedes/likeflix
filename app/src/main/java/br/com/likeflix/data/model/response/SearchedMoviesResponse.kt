package br.com.likeflix.data.model.response

data class SearchedMoviesResponse(
  val page: Double,
  val total_results: Double,
  val total_pages: Double,
  val results: List<MovieResponse>
)

data class MovieResponse(
  val popularity: Double,
  val vote_count: Double,
  val video: Boolean,
  val poster_path: String,
  val id: Double,
  val adult: Boolean,
  val backdrop_path: String? = null,
  val original_language: String,
  val original_title: String,
  val genre_ids: List<Double>,
  val title: String,
  val vote_average: Double,
  val overview: String,
  val release_date: String
)