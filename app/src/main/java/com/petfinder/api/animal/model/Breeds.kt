
package com.petfinder.api.animal.model

import kotlinx.serialization.Serializable

@Serializable
data class Breeds(
    val mixed: Boolean,
    val primary: String,
    val secondary: String?,
    val unknown: Boolean,
)
