package br.com.likeflix.ui.genrefragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.likeflix.R
import br.com.likeflix.domain.MovieBO
import br.com.likeflix.util.loadPic
import org.jetbrains.anko.find

class MoviesAdapter(
    private val onItemSelected: (MovieBO) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.BaseViewHolder<*>>() {

    private var moviesList: MutableList<MovieBO> = mutableListOf()

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val movie = moviesList[position]
        holder.bind(position, movie)
    }

    fun updateMoviesList(list: MutableList<MovieBO>) {
        moviesList.addAll(list)
        notifyDataSetChanged()
    }

    fun setMoviesList(list: MutableList<MovieBO>) {
        moviesList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        moviesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        LayoutInflater
            .from(parent.context)
            .run {
                BaseMovieViewHolder(
                    itemView = inflateLayout(parent, R.layout.movie_item)
                )
            }

    inner class BaseMovieViewHolder(itemView: View) : BaseViewHolder<Any>(itemView) {
        override fun bind(
            position: Int,
            movie: MovieBO
        ) {
            with(itemView) {
                find<TextView>(R.id.tvMovieName).text = movie.title
                find<ImageView>(R.id.ivMovieCover).loadPic(movie.poster_path)
                setOnClickListener {
                    onItemSelected(movie)
                }
            }
        }
    }

    private fun LayoutInflater.inflateLayout(parent: ViewGroup, layoutId: Int): View =
        inflate(layoutId, parent, false)

    abstract class BaseViewHolder<T>(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(
            position: Int,
            movie: MovieBO
        )
    }
}