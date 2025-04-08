package com.bangnv.datastore.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangnv.datastore.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val userPreferences by viewModel.userPreferences.collectAsStateWithLifecycle()
    var usernameInput by remember { mutableStateOf(userPreferences.username) }

    LaunchedEffect(userPreferences.username) {
        usernameInput = userPreferences.username
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cài đặt") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            Text(
//                text = "Thông tin cá nhân",
//                style = MaterialTheme.typography.titleLarge
//            )
//
//            OutlinedTextField(
//                value = usernameInput,
//                onValueChange = { usernameInput = it },
//                label = { Text("Tên người dùng") },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Button(
//                onClick = { viewModel.updateUsername(usernameInput) },
//                modifier = Modifier.align(Alignment.End)
//            ) {
//                Text("Lưu tên người dùng")
//            }
//
//            Text(
//                text = "Giao diện",
//                style = MaterialTheme.typography.titleLarge
//            )
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "Chế độ tối",
//                    style = MaterialTheme.typography.bodyLarge,
//                    modifier = Modifier.weight(1f)
//                )
//                Switch(
//                    checked = userPreferences.isDarkMode,
//                    onCheckedChange = { viewModel.updateThemeMode(it) }
//                )
//            }
//
//
//            Text(
//                text = "Thông báo",
//                style = MaterialTheme.typography.titleLarge
//            )
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "Bật thông báo",
//                    style = MaterialTheme.typography.bodyLarge,
//                    modifier = Modifier.weight(1f)
//                )
//                Switch(
//                    checked = userPreferences.notificationsEnabled,
//                    onCheckedChange = { viewModel.updateNotificationsEnabled(it) }
//                )
//            }
        }
    }
}