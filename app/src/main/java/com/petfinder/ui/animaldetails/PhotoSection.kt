
package com.petfinder.ui.animaldetails

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.petfinder.api.animal.model.Animal
import com.petfinder.api.animal.model.Photo
import com.petfinder.components.LoadingImage
import com.petfinder.theme.AppTheme
import com.petfinder.util.previewAnimal

@Composable
fun PhotoSection(
    animal: Animal,
    selectedImageIndex: Int,
    setSelectedImageIndex: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        CoilImage(
            imageModel = animal.photos[selectedImageIndex].full,
            contentDescription = "${animal.description}",
            contentScale = ContentScale.FillBounds,
            loading = { LoadingImage() },
            failure = { LoadingImage() },
            modifier = Modifier.matchParentSize()
        )
        if (animal.photos.size > 1) {
            val miniPhotoSize = 75.dp
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(18.dp)
            ) {
                animal
                    .photos
                    .take(4)
                    .forEachIndexed { currentPhotoIndex: Int, currentPhoto: Photo ->
                        val photoShape = MaterialTheme.shapes.small
                        val secondaryColor = MaterialTheme.colors.secondary

                        val photoBorder by derivedStateOf {
                            if (currentPhotoIndex == selectedImageIndex) {
                                Modifier
                                    .border(
                                        width = 4.dp,
                                        color = secondaryColor,
                                        shape = photoShape, /*RoundedCornerShape(64.dp)*/
                                    )
                            } else {
                                Modifier
                            }
                        }
                        Box(
                            modifier = Modifier
                                .size(miniPhotoSize)
                                .clip(photoShape)
                                .then(photoBorder)
                                .clickable {
                                    setSelectedImageIndex(currentPhotoIndex)
                                },
                        ) {
                            CoilImage(
                                imageModel = currentPhoto.small,
                                contentDescription = "${animal.description}",
                                contentScale = ContentScale.FillBounds,
                                loading = { LoadingImage() },
                                failure = { LoadingImage() },
                                modifier = Modifier
                                    .size(miniPhotoSize)
                            )
                        }
                    }
            }
        }
    }
}

@Preview
@Composable
fun PhotoSectionPreview() {
    AppTheme {
        PhotoSection(
            animal = previewAnimal,
            selectedImageIndex = 0,
            setSelectedImageIndex = {}
        )
    }
}
