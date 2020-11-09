package br.com.likeflix.domain.usecase

import br.com.likeflix.data.LikeFlixRepository
import br.com.likeflix.domain.SearchedMoviesBO
import br.com.likeflix.util.toQuery
import io.reactivex.Single

class GetMoviesByName(
    private val repository: LikeFlixRepository
) {
    fun execute(movieName: String): Single<SearchedMoviesBO> {
        return repository.getMoviesByName(
            movieName.toQuery()
        )
    }
}