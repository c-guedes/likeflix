package br.com.likeflix.data

sealed class StateResponse<T>

class StateSuccess<T>(val data: T) : StateResponse<T>()
class StateError<T>(val error: Throwable) : StateResponse<T>()
class StateLoading<T> : StateResponse<T>()

fun <T : Any> StateSuccess<*>.getData() = data as T
fun <T : Throwable> StateError<*>.getError() = error as T