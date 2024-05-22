package com.yonasoft.mustacher

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException
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
                    val context = LocalContext.current
                    val arcoreAvailability = ArCoreApk.getInstance().checkAvailability(context).isSupported

                    if(arcoreAvailability){
                        var session: Session?= null
                        var userRequestedInstall = true
                        try{
                            if (session == null){
                                when(ArCoreApk.getInstance().requestInstall(this
                                    , userRequestedInstall)){

                                    ArCoreApk.InstallStatus.INSTALLED -> {
                                        session = Session(this)
                                        val config = Config(session)
                                        session.configure(config)
                                    }

                                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                                        userRequestedInstall = false
                                    }
                                }
                            }
                        } catch (e: UnavailableUserDeclinedInstallationException) {
                            // Display an appropriate message to the user and return gracefully.
                            Toast.makeText(this, "TODO: handle exception $e", Toast.LENGTH_LONG)
                                .show()
                        }
                        MustacherApp(session)
                    }
                    else {
                        Text("AR Core not supported")
                    }
                }
            }
        }
    }
}

@Composable
fun MustacherApp (
    session: Session?
) {

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
                    RecordingScreen(session)
                }
                1 -> {
                    session!!.close()
                    GalleryScreen()
                }
            }
        }
    }
 }
