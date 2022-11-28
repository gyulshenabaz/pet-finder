
package com.petfinder.api

import com.petfinder.api.token.TokenRequest
import com.petfinder.api.token.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("oauth2/token")
    suspend fun getToken(
        @Body tokenRequest: TokenRequest = TokenRequest.create(),
    ): TokenResponse
}
