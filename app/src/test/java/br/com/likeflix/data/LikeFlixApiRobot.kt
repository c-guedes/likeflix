package br.com.likeflix.data

import br.com.likeflix.assertInstanceOf
import br.com.likeflix.data.model.response.SearchedMoviesResponse
import br.com.likeflix.readResource
import io.mockk.every
import io.mockk.mockk

class LikeFlixApiRobot {
    private val subject = mockk<LikeFlixApi>()
    private val response: SearchedMoviesResponse =
        readResource(
            "moviesResponse.json"
        )

    inner class Arrange {
        fun mockSearchMovieByNameWithSuccess() {
            every { subject.searchMovieByName(movieName = any()) } returns response
        }

        fun mockSearchMoviesByGenreWithSuccess() {
            every {
                subject.searchMoviesByGenre(
                    genreId = any(),
                    page = any()
                )
            } returns response
        }

        infix fun act(func: Act.() -> Unit) =
            Act().apply(func)
    }

    inner class Act {
        fun searchMovieByName(): SearchedMoviesResponse {
            return subject.searchMovieByName(movieName = "teste")
        }

        fun searchMovieByGenre(): SearchedMoviesResponse {
            return subject.searchMoviesByGenre(
                genreId = 1,
                page = 1
            )
        }

        infix fun assert(func: Assert.() -> Unit) =
            Assert().apply(func)
    }

    inner class Assert {
        fun isSuccessToGetMoviesList(expectedResponse: SearchedMoviesResponse) {
            expectedResponse assertInstanceOf SearchedMoviesResponse::class.java
        }
    }

    infix fun arrange(func: Arrange.() -> Unit) =
        Arrange().apply(func)
}