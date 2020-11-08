package br.com.likeflix.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.com.likeflix.data.StateError
import br.com.likeflix.data.StateResponse
import br.com.likeflix.data.StateSuccess

fun <T : Any> MutableLiveData<StateResponse<T>>.successData(data: T) {
    value = StateSuccess(data)
}

fun <T : Any> MutableLiveData<StateResponse<T>>.errorData(error: Throwable) {
    value = StateError(error)
}

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (data: T) -> Unit) {
    observe(owner, Observer {
        it?.let(observer)
    })
}