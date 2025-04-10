package com.bangnv.datastore.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangnv.datastore.common.ThemeMode
import com.bangnv.datastore.ui.component.ThemeOption
import com.bangnv.datastore.ui.theme.AppTheme
import com.bangnv.datastore.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val userPreferences by viewModel.userPreferences.collectAsStateWithLifecycle()
    val usernameInput by viewModel.usernameInput.collectAsState()

    // Gọi khi hiển thị lần đầu để sync input với userPreferences
    LaunchedEffect(userPreferences.username) {
        viewModel.setUsernameInput(userPreferences.username)
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
                .padding(16.dp)
                .background(AppTheme.colorScheme.surface)
        ) {
            // Avatar giả
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(Color.Gray, shape = CircleShape)
                    .align(Alignment.CenterHorizontally)
            )

            // Nhập tên
            OutlinedTextField(
                value = usernameInput,
                onValueChange = { viewModel.setUsernameInput(it) },
                label = { Text("Tên người dùng") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (viewModel.canSaveUsername()) {
                        viewModel.updateUsername(usernameInput.trim())
                    }
                },
                modifier = Modifier.align(Alignment.End),
                enabled = viewModel.canSaveUsername()
            ) {
                Text("Lưu tên")
            }

            // Switch thông báo
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Bật thông báo", modifier = Modifier.weight(1f))
                Switch(
                    checked = userPreferences.notificationsEnabled,
                    onCheckedChange = { viewModel.updateNotificationsEnabled(it) }
                )
            }

            // Tuỳ chọn theme
            Text("Chế độ giao diện", style = MaterialTheme.typography.titleMedium)
            ThemeOption(
                label = "Hệ thống",
                isSelected = userPreferences.themeMode == ThemeMode.SYSTEM,
                onClick = { viewModel.updateThemeMode(ThemeMode.SYSTEM) }
            )
            ThemeOption(
                label = "Sáng",
                isSelected = userPreferences.themeMode == ThemeMode.LIGHT,
                onClick = { viewModel.updateThemeMode(ThemeMode.LIGHT) }
            )
            ThemeOption(
                label = "Tối",
                isSelected = userPreferences.themeMode == ThemeMode.DARK,
                onClick = { viewModel.updateThemeMode(ThemeMode.DARK) }
            )
        }
    }
}
