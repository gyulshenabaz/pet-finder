
package com.petfinder.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Male
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.petfinder.theme.AnimalGenderFemale
import com.petfinder.theme.AnimalGenderMale
import com.petfinder.theme.AnimalGenderUnknown

@Composable
fun GenderChip(
    genderString: String,
) {
    val iconData = Gender.fromString(genderString).iconData()
    Icon(
        imageVector = iconData.imageVector,
        tint = iconData.color,
        contentDescription = "Gender Icon",
        modifier = Modifier.size(24.dp)
    )
}

private class IconData(
    val imageVector: ImageVector,
    val color: Color,
)

sealed class Gender {
    companion object {
        @Suppress("RemoveRedundantQualifierName")
        fun fromString(gender: String): Gender = when {
            gender.equals("male", ignoreCase = true) -> Gender.Male
            gender.equals("female", ignoreCase = true) -> Gender.Female
            else -> Gender.Unknown
        }
    }

    object Female : Gender()
    object Male : Gender()
    object Unknown : Gender()
}

@Composable
private fun Gender.iconData() = when (this) {
    Gender.Female -> {
        IconData(
            imageVector = Icons.Default.Female,
            color = AnimalGenderFemale,
        )
    }
    Gender.Male -> {
        IconData(
            imageVector = Icons.Default.Male,
            color = AnimalGenderMale,
        )
    }
    Gender.Unknown -> {
        IconData(
            imageVector = Icons.Default.HelpOutline,
            color = AnimalGenderUnknown,
        )
    }
}
