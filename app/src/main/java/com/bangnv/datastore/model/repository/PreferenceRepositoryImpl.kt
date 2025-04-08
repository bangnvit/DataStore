package com.bangnv.datastore.model.repository

import com.bangnv.datastore.common.ThemeMode
import com.bangnv.datastore.model.datasource.MyPreferenceDataStore
import com.bangnv.datastore.model.dto.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceRepositoryImpl @Inject constructor(
    private val myPrefDataStore: MyPreferenceDataStore
) : PreferenceRepository {

    override val userPreferencesFlow: Flow<UserPreferences> = myPrefDataStore.userPreferencesFlow

    override suspend fun updateThemeMode(themeMode: ThemeMode) {
        myPrefDataStore.updateThemeMode(themeMode)
    }

    override suspend fun updateUsername(username: String) {
        myPrefDataStore.updateUsername(username)
    }

    override suspend fun updateNotificationsEnabled(enabled: Boolean) {
        myPrefDataStore.updateNotificationsEnabled(enabled)
    }
}