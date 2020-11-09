package br.com.likeflix.domain.usecase

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import br.com.likeflix.application.LikeFlixApplication
import br.com.likeflix.data.LikeFlixRepository
import br.com.likeflix.data.model.mapper.SearchedMoviesMapper.responseToBO
import br.com.likeflix.data.model.response.SearchedMoviesResponse
import br.com.likeflix.domain.SearchedMoviesBO
import br.com.likeflix.readResource
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest = Config.NONE)
class GetMoviesByNameTest: AutoCloseKoinTest()  {
    private val repository = mockk<LikeFlixRepository>(relaxed = true)
    private val useCase = GetMoviesByName(repository)
    private val observer = TestObserver<SearchedMoviesBO>()
    private val response: SearchedMoviesBO =
        readResource<SearchedMoviesResponse>(
            "moviesResponse.json"
        ).responseToBO()

    @Test
    fun  execute() {
        every {
            useCase.execute(any())
        } returns Single.just(
            response
        )

        useCase
            .execute("teste")
            .subscribe(observer)
        observer
            .assertComplete()
            .assertNoErrors()
            .assertValue(response)
    }

    @Test
    fun executeWithError() {
        every {
            useCase.execute(any())
        } returns Single.error(Throwable("No data set"))

        useCase
            .execute("teste")
            .subscribe(observer)
        observer
            .assertNotComplete()
            .errors()
            .isNotEmpty()
    }
}