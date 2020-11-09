package br.com.likeflix.data

import br.com.likeflix.domain.SearchedMoviesBO
import io.reactivex.Single

interface LikeFlixRepository {
    fun getMoviesByGenre(genreId: Int, page: Int): Single<SearchedMoviesBO>
    fun getMoviesByName(movieName: String): Single<SearchedMoviesBO>
}