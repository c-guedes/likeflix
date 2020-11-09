package br.com.likeflix.data

import br.com.likeflix.data.model.response.SearchedMoviesResponse
import org.junit.Before
import org.junit.Test

class LikeFlixApiTest {
    private lateinit var robot: LikeFlixApiRobot

    @Before
    fun setup() {
        robot = LikeFlixApiRobot()
    }

    @Test
    fun `should return correct response object when calls searchMovieByName api`() {
        lateinit var response: SearchedMoviesResponse
        robot arrange {
            mockSearchMovieByNameWithSuccess()
        } act {
            response = searchMovieByName()
        } assert {
            isSuccessToGetMoviesList(response)
        }
    }

    @Test
    fun `should return correct response object when calls searchMovieByGenre api`() {
        lateinit var response: SearchedMoviesResponse
        robot arrange {
            mockSearchMoviesByGenreWithSuccess()
        } act {
            response = searchMovieByGenre()
        } assert {
            isSuccessToGetMoviesList(response)
        }
    }
}