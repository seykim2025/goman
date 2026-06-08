package com.goman.screentimelimitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.goman.screentimelimitapp.core.PermissionUtil
import com.goman.screentimelimitapp.ui.MainDashboardScreen
import com.goman.screentimelimitapp.ui.OnboardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val lifecycleOwner = LocalLifecycleOwner.current
                    var allPermissionsGranted by remember { mutableStateOf(PermissionUtil.areAllPermissionsGranted(this)) }

                    DisposableEffect(lifecycleOwner) {
                        val observer = LifecycleEventObserver { _, event ->
                            if (event == Lifecycle.Event.ON_RESUME) {
                                allPermissionsGranted = PermissionUtil.areAllPermissionsGranted(this@MainActivity)
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)
                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }

                    if (allPermissionsGranted) {
                        MainDashboardScreen()
                    } else {
                        OnboardingScreen(onPermissionsGranted = {
                            allPermissionsGranted = PermissionUtil.areAllPermissionsGranted(this@MainActivity)
                        })
                    }
                }
            }
        }
    }
}
