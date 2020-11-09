package br.com.likeflix.ui.home

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [Build.VERSION_CODES.O_MR1]
)
class HomeActivityTest : AutoCloseKoinTest() {
    private lateinit var robot: HomeActivityRobot

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        robot = HomeActivityRobot()
    }

    @Test
    fun `when user search whatever movie should be success`() {
        robot arrange {
            mockWithSuccess()
            setupKoin()
            startActivity()
        } act {
            searchMoviesByName()
        } assert {
            successToGetMoviesList()
        }
    }

    @Test
    fun `when user search whatever movie should be failed`() {
        robot arrange {
            mockWithError()
            setupKoin()
            startActivity()
        } act {
            searchMoviesByName()
        } assert {
            failedToGetMoviesList()
        }
    }

    @Test
    fun `when user enters on home activity should display correctly tabs`() {
        robot arrange {
            setupKoin()
            startActivity()
        } act {
            // do nothing
        } assert {
            isCorrectDisplayingTabs()
        }
    }
}