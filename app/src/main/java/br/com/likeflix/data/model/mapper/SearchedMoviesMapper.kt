package br.com.likeflix.data.model.mapper

import br.com.likeflix.data.model.response.MovieResponse
import br.com.likeflix.data.model.response.SearchedMoviesResponse
import br.com.likeflix.domain.MovieBO
import br.com.likeflix.domain.SearchedMoviesBO

object SearchedMoviesMapper {

    fun SearchedMoviesResponse.responseToBO(): SearchedMoviesBO {
        return SearchedMoviesBO(
            page = page,
            total_results = total_results,
            total_pages = total_pages,
            results = results.toBO()
        )
    }

    private fun List<MovieResponse>.toBO(): List<MovieBO> {
        val converted = mutableListOf<MovieBO>()
        forEach {
            converted.add(
                MovieBO(
                    poster_path = it.poster_path,
                    id = it.id,
                    adult = it.adult,
                    backdrop_path = it.backdrop_path,
                    original_language = it.original_language,
                    original_title = it.original_title,
                    genre_ids = it.genre_ids,
                    title = it.title,
                    release_date = it.release_date,
                    overview = it.overview
                )
            )
        }
        return converted
    }
}