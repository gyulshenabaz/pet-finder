
package com.petfinder.data.authentication

import com.petfinder.api.TokenApi
import com.petfinder.session.SessionManager

class TokenRepositoryImpl(
    private val tokenApi: TokenApi,
    private val sessionManager: SessionManager,
) : TokenRepository {
    override suspend fun getToken(): String? {
        return sessionManager.getCurrentToken()
    }

    override suspend fun refreshAndGetToken(): String {
        val refreshedToken = tokenApi.getToken().accessToken
        sessionManager.setToken(refreshedToken)
        return refreshedToken
    }
}
