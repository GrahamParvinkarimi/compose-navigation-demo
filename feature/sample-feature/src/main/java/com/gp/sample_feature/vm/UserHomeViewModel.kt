package com.gp.sample_feature.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gp.sample_feature.data.UserRepository
import com.gp.sample_feature.viewstate.UserHomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserHomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _userHomeViewState: MutableStateFlow<UserHomeViewState> =
        MutableStateFlow(UserHomeViewState.Initial)

    val userHomeViewState: StateFlow<UserHomeViewState> =
        _userHomeViewState.asStateFlow()

    init {
        loadUserHomeContent()
    }

    fun loadUserHomeContent() {
        _userHomeViewState.value = UserHomeViewState.Loading

        viewModelScope.launch {
            // Simulate delay of a network call to show the loading state
            delay(2000)

            _userHomeViewState.value = UserHomeViewState.Success(users = userRepository.retrieveUsers())
        }
    }
}