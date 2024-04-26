package com.example.todolist

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.TodoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TodoListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Red
                )
                {
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val viewModel: TodoViewModel = viewModel(
                            it,
                            "TodoViewModel",
                            MainViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application
                            )
                        )
                        ScreenSetUp(mainViewModel = viewModel)
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun Preview() {
    val owner = LocalViewModelStoreOwner.current
    owner?.let {
        val viewModel: TodoViewModel = viewModel(
            it,
            "TodoViewModel",
            MainViewModelFactory(
                LocalContext.current.applicationContext as Application
            )
        )
        ScreenSetUp(mainViewModel = viewModel)
    }
}

