package br.com.likeflix.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.likeflix.ui.genrefragment.MoviesGenreViewModelRobot
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    private lateinit var robot: MoviesGenreViewModelRobot

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        robot = MoviesGenreViewModelRobot()
    }

    @Test
    fun `should return state success when tries to retrieve movies`() {
        robot arrange {
            mockWithSuccess()
            injectUseCase()
        } act {
            searchMovies()
        } assert {
            successToGetMoviesList()
        }
    }

    @Test
    fun `should return state error when tries to retrieve movies`() {
        robot arrange {
            mockWithError()
            injectUseCase()
        } act {
            searchMovies()
        } assert {
            failedToGetMoviesList()
        }
    }
}