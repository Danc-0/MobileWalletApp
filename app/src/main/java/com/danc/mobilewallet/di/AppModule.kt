package com.danc.mobilewallet.di

import com.danc.mobilewallet.data.remote.WalletApi
import com.danc.mobilewallet.data.repository.WalletRepositoryImpl
import com.danc.mobilewallet.domain.repository.WalletRepository
import com.danc.mobilewallet.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient: OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()


    @Provides
    @Singleton
    fun providesMobileWallet(): WalletApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WalletApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWalletRepository(api: WalletApi): WalletRepository {
        return WalletRepositoryImpl(api)
    }

}