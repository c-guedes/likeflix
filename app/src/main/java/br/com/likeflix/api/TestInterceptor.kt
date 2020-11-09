package br.com.likeflix.api


import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> doRequest(
    backendCall: () -> Single<T>
): Single<T> {
    return backendCall().defaultSchedulers()
}


private fun <T> singleCallable(backendCall: () -> T): Single<T> =
    Single.fromCallable {
        backendCall.invoke()
    }

private fun <T> Single<T>.defaultSchedulers(): Single<T> =
    subscribeOn(Schedulers.io()).observeOn(ui())

//Main Thread scheduler
private fun ui(): Scheduler = AndroidSchedulers.mainThread()