
package com.petfinder

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.petfinder.theme.AppTheme
import com.petfinder.ui.AppScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppTheme {
                ColoredSystemBarsContent {
                    EdgeToEdgeContent {
                        Surface(
                            color = MaterialTheme.colors.background,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AppScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EdgeToEdgeContent(content: @Composable () -> Unit) {
    ProvideWindowInsets(content = content)
}

@Composable
fun ColoredSystemBarsContent(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val primaryColor = MaterialTheme.colors.primary
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
        )
        systemUiController.setNavigationBarColor(primaryColor)
        systemUiController.setStatusBarColor(primaryColor)
    }
    content()
}
