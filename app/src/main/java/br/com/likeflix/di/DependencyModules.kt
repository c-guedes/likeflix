package br.com.likeflix.di

import br.com.likeflix.api.RetrofitProvider
import br.com.likeflix.data.LikeFlixApi
import br.com.likeflix.data.LikeFlixRepository
import br.com.likeflix.data.LikeFlixRepositoryImpl
import br.com.likeflix.domain.usecase.GetMoviesByGenreUseCase
import br.com.likeflix.ui.genrefragment.MoviesGenreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyModules {
    val useCaseModule = module {
        single { GetMoviesByGenreUseCase(get()) }
    }

    val viewModelModule = module {
        viewModel {
            MoviesGenreViewModel(get())
        }
    }

    val repositoryImpl = module {
        single<LikeFlixRepository> { LikeFlixRepositoryImpl(api = get()) }
    }

    val apiModule = module {
        fun provideRetrofit(): LikeFlixApi {
            return RetrofitProvider.providesRetrofitApi(LikeFlixApi::class.java)
        }
        single { provideRetrofit() }
    }
}