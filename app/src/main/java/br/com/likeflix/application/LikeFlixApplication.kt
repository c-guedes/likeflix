package br.com.likeflix.application

import android.app.Application
import br.com.likeflix.api.RetrofitProvider
import br.com.likeflix.di.DependencyModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


internal open class LikeFlixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRetrofit()
        setupKoin()
    }

    private fun setupKoin() =
        startKoin {
            androidLogger()
            androidContext(this@LikeFlixApplication)
            modules(
                DependencyModules.apiModule,
                DependencyModules.useCaseModule,
                DependencyModules.viewModelModule,
                DependencyModules.repositoryImpl
            )
        }

    protected open fun initRetrofit() = RetrofitProvider.initRetrofit()

}