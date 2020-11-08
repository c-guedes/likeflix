package br.com.likeflix.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.likeflix.data.model.MovieGenre
import br.com.likeflix.ui.genrefragment.MoviesGenreFragment

class GenresPagerAdapter(
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {

    private val genreList: MutableList<MovieGenre> =
        mutableListOf(
            MovieGenre.Action,
            MovieGenre.Drama,
            MovieGenre.Fantasy,
            MovieGenre.ScienceFiction
        )

    override fun getItemCount(): Int = genreList.size

    fun getTabName(position: Int): String = MovieGenre.getNameFromOrdinal(position)

    override fun createFragment(position: Int): Fragment {
        return MoviesGenreFragment.newInstance(
            genreList[position]
        )
    }
}