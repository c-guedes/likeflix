package br.com.likeflix.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.likeflix.BaseActivity
import br.com.likeflix.R
import br.com.likeflix.data.StateResponse
import br.com.likeflix.data.StateSuccess
import br.com.likeflix.domain.MovieBO
import br.com.likeflix.domain.SearchedMoviesBO
import br.com.likeflix.ui.genrefragment.MoviesAdapter
import br.com.likeflix.ui.moviedetail.MovieDetailActivity
import br.com.likeflix.ui.pageAdapter.GenresPagerAdapter
import br.com.likeflix.util.bind
import br.com.likeflix.util.nonNullObserve
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main_tabs.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.koin.android.ext.android.inject


class HomeActivity : BaseActivity(
    R.layout.activity_main_tabs
), SearchView.OnQueryTextListener {

    private val viewModel by inject<HomeViewModel>()

    private val viewPagerAdapter: ViewPager2 by bind(R.id.vpMovies)
    private val tabs: TabLayout by bind(R.id.tabMovieGenre)
    private val rvSearchMovie: RecyclerView by bind(R.id.rvSearchMovie)
    private lateinit var searchAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
        setupObservable()
        setupSearchRecycler()
    }

    private fun setupSearchRecycler() {
        searchAdapter = MoviesAdapter(onItemSelected = {
            startDetailActivity(it)
        })
        rvSearchMovie.adapter = searchAdapter
    }

    private fun setupObservable() {
        viewModel.getMoviesSearched.nonNullObserve(this) {
            handleSearchedMovieQuery(it)
        }
    }

    private fun handleSearchedMovieQuery(response: StateResponse<SearchedMoviesBO>) {
        when (response) {
            is StateSuccess -> searchAdapter.setMoviesList(response.data.results.toMutableList())
        }
    }

    private fun setupViewPager() {
        viewPagerAdapter.adapter = GenresPagerAdapter(this)
        TabLayoutMediator(tabs, viewPagerAdapter) { tab, position ->
            tab.text = (viewPagerAdapter.adapter as GenresPagerAdapter).getTabName(position)
        }.attach()
        vpMovies.isUserInputEnabled = false
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.movies_menu, menu)

        with(menu.findItem(R.id.search)) {
            (this.actionView as SearchView).configureSearchView()
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun SearchView.configureSearchView() {
        queryHint = getString(R.string.queryMovie)
        setOnQueryTextListener(this@HomeActivity)
        setOnCloseListener {
            rvSearchMovie.isVisible = false
            false
        }
    }

    private fun startDetailActivity(movieBO: MovieBO) {
        with(Intent(this, MovieDetailActivity::class.java)) {
            putExtra(MovieDetailActivity.MOVIE_BO, movieBO)
            clearTop()
            clearTask()
        }.also {
            startActivity(it)
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        if (newText.length >= DEFAULT_INDEX) {
            viewModel.searchMovieByName(newText)
            rvSearchMovie.isVisible = true
        }
        return true
    }

    companion object {
        private const val DEFAULT_INDEX = 2
    }
}