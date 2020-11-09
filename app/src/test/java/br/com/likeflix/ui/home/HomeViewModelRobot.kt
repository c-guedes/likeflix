package br.com.likeflix.ui.home

import br.com.likeflix.assertInstanceOf
import br.com.likeflix.data.StateError
import br.com.likeflix.data.StateSuccess
import br.com.likeflix.data.model.mapper.SearchedMoviesMapper.responseToBO
import br.com.likeflix.data.model.response.SearchedMoviesResponse
import br.com.likeflix.domain.SearchedMoviesBO
import br.com.likeflix.domain.usecase.GetMoviesByName
import br.com.likeflix.readResource
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single

class HomeViewModelRobot {
    lateinit var viewModel: HomeViewModel
    private val useCase = mockk<GetMoviesByName>(relaxed = true)
    private val useCaseResponse: SearchedMoviesBO =
        readResource<SearchedMoviesResponse>(
            "moviesResponse.json"
        ).responseToBO()

    inner class Arrange {
        fun injectUseCase() {
            viewModel = HomeViewModel(
                useCase
            )
        }

        fun mockWithSuccess() {
            every { useCase.execute(any()) } returns Single.just(useCaseResponse)
        }

        fun mockWithError() {
            every { useCase.execute(any()) } returns Single.error(Throwable())
        }

        infix fun act(func: Act.() -> Unit) =
            Act().apply(func)
    }

    inner class Act {
        fun searchMoviesByName() {
            viewModel.searchMovieByName("qualquer")
        }

        infix fun assert(func: Assert.() -> Unit) =
            Assert().apply(func)
    }

    inner class Assert {
        fun successToGetMoviesList() {
            viewModel.getMoviesSearched.value assertInstanceOf StateSuccess::class.java
        }

        fun failedToGetMoviesList() {
            viewModel.getMoviesSearched.value assertInstanceOf StateError::class.java
        }
    }

    infix fun arrange(func: Arrange.() -> Unit) =
        Arrange().apply(func)
}