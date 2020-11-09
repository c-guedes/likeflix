package br.com.likeflix.data

import br.com.likeflix.api.doRequest
import br.com.likeflix.data.model.mapper.SearchedMoviesMapper.responseToBO
import br.com.likeflix.domain.SearchedMoviesBO
import io.reactivex.Single

class LikeFlixRepositoryImpl(
    private val api: LikeFlixApi
) : LikeFlixRepository {
    override fun getMoviesByGenre(genreId: Int, page: Int): Single<SearchedMoviesBO> =
        doRequest {
            api.searchMoviesByGenre(
                genreId = genreId,
                page = page
            )
        }.map { it.responseToBO() }

    override fun getMoviesByName(movieName: String): Single<SearchedMoviesBO> =
        doRequest {
            api.searchMovieByName(
                movieName = movieName
            )
        }.map { it.responseToBO() }

}