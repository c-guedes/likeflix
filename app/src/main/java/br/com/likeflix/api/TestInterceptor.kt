package br.com.likeflix.api


import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> doRequest(
    hasChainedRequest: Boolean = false,
    doOnError: (() -> Unit)? = null,
    backendCall: () -> Single<T>
): Single<T> {
    return backendCall()
        .defaultSchedulers()
        .requestIt(hasChainedRequest, doOnError)
}

private fun <T> Single<T>.requestIt(
    hasChainedRequest: Boolean,
    doOnError: (() -> Unit)?
): Single<T> {
    return doOnSubscribe {}.doFinally { }.doOnError { doOnError?.invoke() }
}

//Main Thread scheduler
fun ui(): Scheduler = AndroidSchedulers.mainThread()

fun <T> Single<T>.defaultSchedulers(): Single<T> = subscribeOn(Schedulers.io()).observeOn(ui())