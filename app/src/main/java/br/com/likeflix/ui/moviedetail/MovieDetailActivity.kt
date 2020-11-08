package br.com.likeflix.ui.moviedetail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import br.com.likeflix.BaseActivity
import br.com.likeflix.R
import br.com.likeflix.customview.SimpleHeader
import br.com.likeflix.domain.MovieBO
import br.com.likeflix.util.bind
import br.com.likeflix.util.extraOrThrow
import br.com.likeflix.util.loadPic


class MovieDetailActivity : BaseActivity(
    R.layout.activity_movie_detail
) {

    private val ivMovieCover: ImageView by bind(R.id.ivMovieCover)
    private val sHMovieDetail: SimpleHeader by bind(R.id.sHMovieDetail)
    private val tvMovieOverview: TextView by bind(R.id.tvMovieOverview)
    private val movieBO: MovieBO by extraOrThrow(MOVIE_BO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(movieBO) {
            ivMovieCover.loadPic(imagePath = poster_path)
            tvMovieOverview.text = overview
            sHMovieDetail.updateHeader(title)
        }
    }

    companion object {
        const val MOVIE_BO = "MOVIE_KEY"
    }
}