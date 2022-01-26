package com.example.cookpadapp.di

import android.content.Context
import com.example.cookpadapp.CookpadRepository
import com.example.cookpadapp.internet.CookpadInterface
import com.example.cookpadapp.utils.Constants
import com.example.cookpadapp.viewmodel.CookpadViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Modules {

    val viewModels = module {
        viewModel { CookpadViewModel(get()) }
    }

    val apiModule = module {

        factory { CookpadRepository(get()) }

        single<CookpadInterface> {
            provideRetrofit(get<OkHttpClient>())
        }

        factory<OkHttpClient> {
            provideOkHttpClient(get<Cache>())
        }

        factory<Cache> {
            provideCache(get<Context>())
        }
    }
}


fun provideCache(context: Context): Cache {
    val cacheSize: Long = 10 * 1024 * 1024
    return Cache(context.cacheDir, cacheSize)
}

fun provideOkHttpClient(cache: Cache): OkHttpClient {
    val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .header("Cookpad-Url", Constants.COOKPAD_URL)
            chain.proceed(newRequest.build())
        }
        .addInterceptor(logger)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): CookpadInterface {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()
        .create(CookpadInterface::class.java)
}