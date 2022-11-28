
package com.petfinder.api.animal.model

import kotlinx.serialization.Serializable

@Serializable
data class PrimaryPhotoCropped(
    val full: String,
    val large: String,
    val medium: String,
    val small: String,
)
