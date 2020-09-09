package com.moose.gads.di

import com.moose.gads.BuildConfig
import com.moose.gads.network.GadsEndpoints
import com.moose.gads.network.GoogleFormEndpoints
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single(named("gadsRetrofit")) { provideRetrofit(client = get(), url = "https://gadsapi.herokuapp.com/") }
    single { provideGadsService(gadsRetrofit = get(named("gadsRetrofit"))) }
    single(named("googleRetrofit")) { provideRetrofit(client = get(), url = "https://docs.google.com/forms/d/e/") }
    single { provideGoogleService(googleRetrofit = get(named("googleRetrofit"))) }
    single { provideClient() }

}


internal fun provideClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
}

internal fun provideRetrofit(client: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}

internal fun provideGadsService(gadsRetrofit: Retrofit): GadsEndpoints {
    return gadsRetrofit.create(GadsEndpoints::class.java)
}

fun provideGoogleService(googleRetrofit: Retrofit): GoogleFormEndpoints {
    return googleRetrofit.create(GoogleFormEndpoints::class.java)
}
