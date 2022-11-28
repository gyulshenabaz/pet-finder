
package com.petfinder.data.authentication

interface TokenRepository {
    suspend fun getToken(): String?
    suspend fun refreshAndGetToken(): String
}
