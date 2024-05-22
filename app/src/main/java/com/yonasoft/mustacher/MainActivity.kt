package com.yonasoft.mustacher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yonasoft.mustacher.presentation.components.bottom_nav_bar.BottomNavBar
import com.yonasoft.mustacher.presentation.screens.gallery_screen.GalleryScreen
import com.yonasoft.mustacher.presentation.screens.recording_screen.RecordingScreen
import com.yonasoft.mustacher.ui.theme.MustacherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MustacherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MustacherApp()
                }
            }
        }
    }
}

@Composable
fun MustacherApp () {

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(selectedItemIndex = selectedItemIndex) { index ->
                selectedItemIndex = index
            }
        },
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

            when (selectedItemIndex) {
                0 -> {
                    RecordingScreen()
                }
                1 -> {
                    GalleryScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MustacherTheme {
        MustacherApp()
    }
}