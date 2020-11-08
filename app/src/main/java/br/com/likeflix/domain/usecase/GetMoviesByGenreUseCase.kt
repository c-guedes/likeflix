package br.com.likeflix.domain.usecase

import br.com.likeflix.data.LikeFlixRepository
import br.com.likeflix.domain.SearchedMoviesBO
import io.reactivex.Single

class GetMoviesByGenreUseCase(
    private val repository: LikeFlixRepository
) {
    fun execute(genreId: Int, page: Int): Single<SearchedMoviesBO> {
        return repository.getMoviesByGenre(genreId, page)
    }
}