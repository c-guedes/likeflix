package br.com.likeflix.di

import br.com.likeflix.api.RetrofitProvider
import br.com.likeflix.data.LikeFlixApi
import br.com.likeflix.data.LikeFlixRepositoryImpl
import org.koin.dsl.module

object DependencyModules {
    val useCaseModule = module {
//        single { LoginUseCase(get()) }
    }

    val viewModelModule = module {
//        viewModel {
//            Example(get(), get(), get())
//        }
    }

    val repositoryImpl = module {
        single { LikeFlixRepositoryImpl(api = get(), context = get()) }
    }

    val apiModule = module {
        fun provideRetrofit(): LikeFlixApi {
            return RetrofitProvider.providesRetrofitApi(LikeFlixApi::class.java)
        }
        single { provideRetrofit() }
    }
}