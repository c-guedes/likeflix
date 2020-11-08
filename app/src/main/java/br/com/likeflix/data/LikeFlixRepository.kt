package br.com.likeflix.data

import br.com.likeflix.domain.MovieDetailBO
import br.com.likeflix.domain.SearchedMoviesBO
import io.reactivex.Single

interface LikeFlixRepository {
    fun getMovieDetail(): Single<MovieDetailBO>
    fun getMoviesByGenre(genreId: Int, page: Int): Single<SearchedMoviesBO>
}