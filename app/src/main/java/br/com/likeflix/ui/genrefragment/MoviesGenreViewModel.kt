package br.com.likeflix.ui.genrefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.likeflix.BaseViewModel
import br.com.likeflix.data.StateLoading
import br.com.likeflix.data.StateResponse
import br.com.likeflix.domain.SearchedMoviesBO
import br.com.likeflix.domain.usecase.GetMoviesByGenreUseCase
import br.com.likeflix.util.errorData
import br.com.likeflix.util.successData

class MoviesGenreViewModel(
    private val getMoviesUseCase: GetMoviesByGenreUseCase
) : BaseViewModel() {
    private val _getMovies = MutableLiveData<StateResponse<SearchedMoviesBO>>()
    val getMovies: LiveData<StateResponse<SearchedMoviesBO>> get() = _getMovies

    private var page = 1

    fun getMoviesWithGenre(genreId: Int) {
        getMoviesUseCase.execute(genreId, page)
            .doOnSubscribe { _getMovies.value = StateLoading() }
            .subscribe(
                {
                    _getMovies.successData(it)
                    page += 1
                }, {
                    _getMovies.errorData(it)
                }
            ).also {
                disposables.add(it)
            }
    }
}