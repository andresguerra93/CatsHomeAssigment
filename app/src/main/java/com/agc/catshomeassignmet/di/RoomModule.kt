package com.agc.catshomeassignmet.di

import android.content.Context
import androidx.room.Room
import com.agc.catshomeassignmet.data.local.CatsDataBase
import com.agc.catshomeassignmet.data.local.dao.CatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val CAT_DATABASE_NAME = "cat_database"
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CatsDataBase::class.java, CAT_DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideCatDao(catDatabase: CatsDataBase): CatDao {
        return catDatabase.catDao()
    }
}