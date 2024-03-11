package com.agc.catshomeassignmet.di

import com.agc.catshomeassignmet.BuildConfig.BASE_URL
import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.data.local.dao.CatDao
import com.agc.catshomeassignmet.data.network.CatsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCatsApiService(retrofit: Retrofit): CatsApiService {
        return retrofit.create(CatsApiService::class.java)
    }
    @Provides
    fun provideCatsRepository(catsApiService: CatsApiService, catDao: CatDao): CatsRepository {
        return CatsRepository(catsApiService, catDao)
    }
}