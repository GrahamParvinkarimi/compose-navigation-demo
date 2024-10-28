package com.gp.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.gp.navigationdemo.ui.theme.NavigationDemoTheme
import com.gp.sample_feature.navigation.UserNavHost
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavigationDemoTheme {
                // Main Scaffold containing the TopAppBar and the NavHost
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.navigation_demo_title)) },
                            navigationIcon =
                            {
                                IconButton(onClick = { navController.navigateUp() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = stringResource(R.string.navigation_back_content_description)
                                    )
                                }
                            }
                        )
                    }
                )
                { padding ->
                    UserNavHost(navController = navController, modifier = Modifier.padding(padding))
                }
            }
        }
    }
}