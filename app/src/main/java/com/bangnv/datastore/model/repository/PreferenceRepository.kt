package com.bangnv.datastore.model.repository

import com.bangnv.datastore.common.ThemeMode
import com.bangnv.datastore.model.dto.UserPreferences
import kotlinx.coroutines.flow.Flow

interface  PreferenceRepository {
    val userPreferencesFlow: Flow<UserPreferences>

    suspend fun updateThemeMode(themeMode: ThemeMode)
    suspend fun updateUsername(username: String)
    suspend fun updateNotificationsEnabled(enabled: Boolean)
}