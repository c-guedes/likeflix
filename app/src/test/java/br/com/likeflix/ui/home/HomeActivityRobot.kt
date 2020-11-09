package br.com.likeflix.ui.home

import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.likeflix.*
import br.com.likeflix.data.StateError
import br.com.likeflix.data.StateSuccess
import br.com.likeflix.data.model.mapper.SearchedMoviesMapper.responseToBO
import br.com.likeflix.data.model.response.SearchedMoviesResponse
import br.com.likeflix.di.DependencyModules
import br.com.likeflix.domain.SearchedMoviesBO
import br.com.likeflix.domain.usecase.GetMoviesByName
import com.google.android.material.tabs.TabLayout
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.jetbrains.anko.find
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.robolectric.shadows.ShadowActivity

class HomeActivityRobot {
    private val koinModules =
        listOf<Module>(DependencyModules.useCaseModule, DependencyModules.viewModelModule)
    lateinit var roboletricBuilder: RoboletricBuilder<HomeActivity>
    lateinit var viewModel: HomeViewModel
    lateinit var subject: HomeActivity
    lateinit var shadow: ShadowActivity
    private val useCase = mockk<GetMoviesByName>(relaxed = true, relaxUnitFun = true)
    private val useCaseResponse: SearchedMoviesBO =
        readResource<SearchedMoviesResponse>(
            "moviesResponse.json"
        ).responseToBO()

    inner class Arrange {
        fun setupKoin() {
            loadKoinModules(koinModules)
        }

        fun startActivity() {
            viewModel = spyk(HomeViewModel(useCase))
            roboletricBuilder = startActivityPausingLooper()
            subject = roboletricBuilder.spykActivity
            shadow = roboletricBuilder.shadowActivity
            subject.supportFragmentManager.executePendingTransactions()
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

        fun clickOnSearchView() {
            subject.find<SearchView>(R.id.search).performClick()
        }

        fun setQueryOnSearchView() {
            subject.find<SearchView>(R.id.search).setQuery("teste", false)
        }

        infix fun assert(func: Assert.() -> Unit) =
            Assert().apply(func)
    }

    inner class Assert {
        private val tabs: TabLayout get() = subject.find(R.id.tabMovieGenre)
        private val rvSearchMovie: RecyclerView get() = subject.find(R.id.rvSearchMovie)

        fun successToGetMoviesList() {
            viewModel.getMoviesSearched.value assertInstanceOf StateSuccess::class.java
        }

        fun failedToGetMoviesList() {
            viewModel.getMoviesSearched.value assertInstanceOf StateError::class.java
        }

        fun isCorrectDisplayingTabs() {
            assertEquals(4, tabs.tabCount)
        }
    }

    infix fun arrange(func: Arrange.() -> Unit) =
        Arrange().apply(func)
}