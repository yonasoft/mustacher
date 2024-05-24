package com.yonasoft.mustacher.presentation.screens.recording_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.ar.core.CameraConfig
import com.google.ar.core.CameraConfigFilter
import com.google.ar.core.Config
import com.google.ar.core.Frame
import com.yonasoft.mustacher.R
import io.github.sceneview.SceneView
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.createAnchorOrNull
import io.github.sceneview.ar.arcore.isValid
import io.github.sceneview.ar.node.AnchorNode
import io.github.sceneview.math.Position
import io.github.sceneview.model.ModelInstance
import io.github.sceneview.node.ModelNode
import io.github.sceneview.node.Node
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes
import io.github.sceneview.rememberOnGestureListener

@Composable
fun RecordingScreen() {
    val context =

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        MustacheMenu(modifier = Modifier.align(Alignment.BottomCenter))
    }

}

@Composable
fun MustacheMenu(
    modifier: Modifier = Modifier,
    ) {

    val context = LocalContext.current
    val items = listOf(
        Mustache(0, R.drawable.m1),
        Mustache(1, R.drawable.m2),
        Mustache(2, R.drawable.m3),
        Mustache(3, R.drawable.m4),
        Mustache(4, R.drawable.m5),
        Mustache(5, R.drawable.m6),
        Mustache(6, R.drawable.m7),
        Mustache(7, R.drawable.m8),
        Mustache(8, R.drawable.m9),
        Mustache(9, R.drawable.m10),
        Mustache(10, R.drawable.m11),
        Mustache(11, R.drawable.m12),
    )

    var currentIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val currentMustache = items[currentIndex]

    Row(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = {
            currentIndex = (currentIndex - 1 + items.size) % items.size
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "previous", tint = Color.LightGray)
        }

        CircularImage(imageId = currentMustache.imageId)

        IconButton(
            onClick = {
            currentIndex = (currentIndex + 1 + items.size) % items.size
        }) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "forward", tint = Color.LightGray)
        }
    }
}

@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    imageId: Int,
) {
    Box(
        modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(color = Color.Cyan)
            .border(
                width = 3.dp,
                color = Color(0x97727272),
                shape = CircleShape
            ),
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}


@Composable
fun ARArea(){


}
data class Mustache(var id: Int, var imageId: Int)
