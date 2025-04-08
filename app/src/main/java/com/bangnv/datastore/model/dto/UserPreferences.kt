package com.bangnv.datastore.model.dto

import com.bangnv.datastore.common.ThemeMode

data class UserPreferences(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val username: String = "",
    val notificationsEnabled: Boolean = true
)