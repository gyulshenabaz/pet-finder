
package com.petfinder.api.authenticator

import com.petfinder.session.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class ExistingTokenAppendingInterceptor(
    private val sessionManager: SessionManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentToken = runBlocking {
            sessionManager.getCurrentToken()
        }
        return if (currentToken == null) {
            chain.proceed(chain.request())
        } else {
            chain.proceed(chain.request().withAuthenticationToken(currentToken))
        }
    }
}
