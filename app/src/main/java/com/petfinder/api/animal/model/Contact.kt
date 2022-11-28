
package com.petfinder.api.animal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val address: Address,
    val email: String?,
    val phone: String?,
) {
    @Serializable
    data class Address(
        @SerialName("address1")
        val address: String?,
        val city: String,
        val country: String,
        val postcode: String?,
        val state: String,
    )
}
