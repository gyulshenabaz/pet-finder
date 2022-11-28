
package com.petfinder.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.petfinder.R
import com.petfinder.theme.AppTheme
import com.petfinder.theme.MyHappyEndingFont

@Composable
fun FindMyPetAppBar() {
    Surface(
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary,
    ) {
        TopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        space = (-4).dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.Pets,
                        contentDescription = "Pet icon",
                        tint = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier
                            .scale(1.22f)
                            .graphicsLayer(
                                translationX = -25f,
                                rotationZ = -20f
                            )
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        textAlign = TextAlign.Center,
                        fontFamily = MyHappyEndingFont,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.h3,
                    )
                }
            },
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onSurface,
            elevation = 0.dp,
            modifier = Modifier.statusBarsPadding()
        )
    }
}

@Preview
@Composable
fun FindMyPetAppBarPreview() {
    AppTheme {
        FindMyPetAppBar()
    }
}
