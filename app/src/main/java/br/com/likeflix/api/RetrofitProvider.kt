package br.com.likeflix.api

import br.com.likeflix.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private lateinit var retrofit: Retrofit

    internal fun <T> providesRetrofitApi(clazz: Class<T>): T = retrofit.create(clazz)

    internal fun initRetrofit() {
        val httpClient = providesOkHttpClient()
        retrofit = providesRetrofitClient(httpClient)
    }

    private fun providesOkHttpClient(): OkHttpClient {
        return providesOkHttpClientBuilder().build()
    }

    private fun providesOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    private fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}
