
package xyz.database.data.animal.model

import androidx.room.Embedded

data class LinksEntity(
    @Embedded(prefix = "organization_") val organizationEntity: OrganizationEntity,
    @Embedded(prefix = "self_") val selfEntity: SelfEntity,
    @Embedded(prefix = "type_") val typeEntity: TypeEntity,
) {
    data class OrganizationEntity(
        val href: String,
    )

    data class SelfEntity(
        val href: String,
    )

    data class TypeEntity(
        val href: String,
    )
}
