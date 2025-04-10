package com.bangnv.datastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangnv.datastore.common.ThemeMode
import com.bangnv.datastore.model.dto.UserPreferences
import com.bangnv.datastore.model.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    val userPreferences: StateFlow<UserPreferences> = preferenceRepository.userPreferencesFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserPreferences()
        )

    private val _usernameInput = MutableStateFlow("")
    val usernameInput: StateFlow<String> = _usernameInput

    fun setUsernameInput(input: String) {
        _usernameInput.value = input
    }

    fun canSaveUsername(): Boolean {
        val input = _usernameInput.value.trim()
        return input.isNotEmpty() && input != userPreferences.value.username
    }


    fun updateThemeMode(themeMode: ThemeMode) {
        viewModelScope.launch {
            preferenceRepository.updateThemeMode(themeMode)
        }
    }

    fun updateUsername(username: String) {
        viewModelScope.launch {
            preferenceRepository.updateUsername(username)
        }
    }

    fun updateNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferenceRepository.updateNotificationsEnabled(enabled)
        }
    }
}