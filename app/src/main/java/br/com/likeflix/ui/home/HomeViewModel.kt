package br.com.likeflix.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.likeflix.BaseViewModel
import br.com.likeflix.data.StateLoading
import br.com.likeflix.data.StateResponse
import br.com.likeflix.domain.SearchedMoviesBO
import br.com.likeflix.domain.usecase.GetMoviesByName
import br.com.likeflix.util.errorData
import br.com.likeflix.util.successData

class HomeViewModel(
    private val getMoviesByName: GetMoviesByName
) : BaseViewModel() {
    private val _getMoviesSearched = MutableLiveData<StateResponse<SearchedMoviesBO>>()
    val getMoviesSearched: LiveData<StateResponse<SearchedMoviesBO>> get() = _getMoviesSearched

    fun searchMovieByName(movieName: String) {
        getMoviesByName.execute(movieName)
            .doOnSubscribe { _getMoviesSearched.value = StateLoading() }
            .subscribe(
                {
                    _getMoviesSearched.successData(it)
                }, {
                    _getMoviesSearched.errorData(it)
                }
            ).also {
                disposables.add(it)
            }
    }
}