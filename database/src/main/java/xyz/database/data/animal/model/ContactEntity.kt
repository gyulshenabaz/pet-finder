
package xyz.database.data.animal.model

import androidx.room.Embedded

data class ContactEntity(
    @Embedded val addressEntity: AddressEntity,
    val email: String?,
    val phone: String?,
) {
    data class AddressEntity(
        val address: String?,
        val city: String,
        val country: String,
        val postcode: String?,
        val state: String,
    )
}
