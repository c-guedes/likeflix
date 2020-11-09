package br.com.likeflix.data

import br.com.likeflix.API_KEY
import br.com.likeflix.DEFAULT_LANGUAGE
import br.com.likeflix.data.model.response.MovieDetailResponse
import br.com.likeflix.data.model.response.SearchedMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface LikeFlixApi {
    @GET("search/movie")
    fun searchMovieByName(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("query", encoded = true) movieName: String
    ): SearchedMoviesResponse

    @GET("discover/movie")
    fun searchMoviesByGenre(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): SearchedMoviesResponse
}