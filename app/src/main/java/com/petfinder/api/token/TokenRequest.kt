
package com.petfinder.api.token

import com.petfinder.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TokenRequest private constructor(
    @SerialName("grant_type")
    val grantType: String,
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String,
) {
    companion object {
        fun create(): TokenRequest =
            TokenRequest(
                grantType = "client_credentials",
                clientId = BuildConfig.PET_FINDER_API_KEY,
                clientSecret = BuildConfig.PET_FINDER_SECRET,
            )
    }
}
