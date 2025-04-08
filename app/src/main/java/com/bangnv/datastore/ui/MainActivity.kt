package com.bangnv.datastore.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangnv.datastore.ui.navigation.NavGraph
import com.bangnv.datastore.ui.theme.DataStoreAppTheme
import com.bangnv.datastore.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val userPreferences by settingsViewModel.userPreferences.collectAsStateWithLifecycle()
            val themeMode = userPreferences.themeMode

            DataStoreAppTheme(themeMode = themeMode) {
                NavGraph()
            }
        }
    }
}