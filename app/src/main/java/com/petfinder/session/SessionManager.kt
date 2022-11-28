
package com.petfinder.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val AUTHENTICATION_TOKEN_KEY = "authentication_token"

class SessionManager @Inject constructor(
    private val preferencesDataStore: DataStore<Preferences>
) {
    private val authenticationStringKey = stringPreferencesKey(AUTHENTICATION_TOKEN_KEY)

    private fun getTokenFlow(): Flow<String?> =
        preferencesDataStore
            .data
            .map { preferences: Preferences ->
                preferences[authenticationStringKey]
            }

    suspend fun getCurrentToken(): String? {
        return getTokenFlow().first()
    }

    suspend fun setToken(
        token: String,
    ) {
        preferencesDataStore.edit { mutablePreferences: MutablePreferences ->
            mutablePreferences[authenticationStringKey] = token
        }
    }
}
