package br.com.likeflix.ui.genrefragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.likeflix.BaseFragment
import br.com.likeflix.R
import br.com.likeflix.data.StateSuccess
import br.com.likeflix.data.model.MovieGenre
import br.com.likeflix.domain.MovieBO
import br.com.likeflix.ui.moviedetail.MovieDetailActivity
import br.com.likeflix.ui.moviedetail.MovieDetailActivity.Companion.MOVIE_BO
import br.com.likeflix.util.bind
import br.com.likeflix.util.nonNullObserve
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.koin.android.ext.android.inject


class MoviesGenreFragment(
    private val genreType: MovieGenre
) : BaseFragment(
    R.layout.simple_fragment_genre
) {
    private val viewModel by inject<MoviesGenreViewModel>()

    private val rvMovies: RecyclerView by bind(R.id.rvMovies)
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var rvLayoutManager: GridLayoutManager
    var isLoading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservable()
        viewModel.getMoviesWithGenre(genreType.id)
        context?.let {
            moviesAdapter = MoviesAdapter(onItemSelected = {
                startDetailActivity(it)
            })
        }
        setupRv()
    }

    private fun startDetailActivity(movieBO: MovieBO) {
        with(Intent(activity, MovieDetailActivity::class.java)) {
            putExtra(MOVIE_BO, movieBO)
            clearTop()
            clearTask()
        }.also {
            startActivity(it)
        }
    }

    private fun setupObservable() {
        viewModel.getMovies.nonNullObserve(this) { state ->
            when (state) {
                is StateSuccess -> {
                    moviesAdapter.updateMoviesList(
                        state.data.results.toMutableList()
                    )
                    isLoading = false
                }
            }
        }
    }

    private fun setupRv() {
        rvLayoutManager = GridLayoutManager(activity, 2)
        context?.let {
            rvMovies.apply {
                adapter = moviesAdapter
                layoutManager = rvLayoutManager
            }
        }

        rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadData()
                }
            }
        })
    }

    private fun loadData() {
        if (!isLoading) viewModel.getMoviesWithGenre(genreType.id)
    }

    companion object {
        fun newInstance(genreType: MovieGenre) =
            MoviesGenreFragment(genreType)
    }
}