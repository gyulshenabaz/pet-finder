
package com.petfinder.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.petfinder.session.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val API_URL_QUALIFIER = "API_URL_QUALIFIER"
    const val LOGGING_INTERCEPTOR_QUALIFIER = "LOGGING_INTERCEPTOR_QUALIFIER"

    @Singleton
    @Provides
    @Named(API_URL_QUALIFIER)
    fun providePetFinderBaseApiRoute(): String = "https://api.petfinder.com/v2/"

    @Singleton
    @Provides
    fun provideSessionManager(
        preferencesDataStore: DataStore<Preferences>,
    ): SessionManager =
        SessionManager(preferencesDataStore)

    @Singleton
    @Provides
    @Named(LOGGING_INTERCEPTOR_QUALIFIER)
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

    @Singleton
    @Provides
    fun provideKotlinxSerializer(): Json =
        Json {
            ignoreUnknownKeys = true
        }
}
