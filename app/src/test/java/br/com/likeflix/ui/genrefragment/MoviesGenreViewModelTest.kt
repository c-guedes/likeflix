package br.com.likeflix.ui.genrefragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.likeflix.ui.home.HomeViewModelRobot
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesGenreViewModelTest {
    private lateinit var robot: HomeViewModelRobot

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        robot = HomeViewModelRobot()
    }

    @Test
    fun `should return state success when tries to retrieve movies`() {
        robot arrange {
            mockWithSuccess()
            injectUseCase()
        } act {
            searchMoviesByName()
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
            searchMoviesByName()
        } assert {
            failedToGetMoviesList()
        }
    }
}