package br.com.likeflix.ui.genrefragment

import br.com.likeflix.assertInstanceOf
import br.com.likeflix.data.StateError
import br.com.likeflix.data.StateSuccess
import br.com.likeflix.data.model.mapper.SearchedMoviesMapper.responseToBO
import br.com.likeflix.data.model.response.SearchedMoviesResponse
import br.com.likeflix.domain.SearchedMoviesBO
import br.com.likeflix.domain.usecase.GetMoviesByGenreUseCase
import br.com.likeflix.readResource
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single

class MoviesGenreViewModelRobot {
    lateinit var viewModel: MoviesGenreViewModel
    private val useCase = mockk<GetMoviesByGenreUseCase>(relaxed = true)
    private val useCaseResponse: SearchedMoviesBO =
        readResource<SearchedMoviesResponse>(
            "moviesResponse.json"
        ).responseToBO()

    inner class Arrange {
        fun injectUseCase() {
            viewModel = MoviesGenreViewModel(
                useCase
            )
        }

        fun mockWithSuccess() {
            every { useCase.execute(any(), any()) } returns Single.just(useCaseResponse)
        }

        fun mockWithError() {
            every { useCase.execute(any(), any()) } returns Single.error(Throwable())
        }

        infix fun act(func: Act.() -> Unit) =
            Act().apply(func)
    }

    inner class Act {
        fun searchMovies() {
            viewModel.getMoviesWithGenre(1)
        }

        infix fun assert(func: Assert.() -> Unit) =
            Assert().apply(func)
    }

    inner class Assert {
        fun successToGetMoviesList() {
            viewModel.getMovies.value assertInstanceOf StateSuccess::class.java
        }

        fun failedToGetMoviesList() {
            viewModel.getMovies.value assertInstanceOf StateError::class.java
        }
    }

    infix fun arrange(func: Arrange.() -> Unit) =
        Arrange().apply(func)
}