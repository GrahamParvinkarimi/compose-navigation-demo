package com.gp.sample_feature.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.gp.sample_feature.data.UserRepository
import com.gp.sample_feature.navigation.UserDetails
import com.gp.sample_feature.viewstate.UserDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _userDetailsViewState: MutableStateFlow<UserDetailsViewState> =
        MutableStateFlow(UserDetailsViewState.Initial)

    val userDetailsViewState: StateFlow<UserDetailsViewState> =
        _userDetailsViewState.asStateFlow()

    init {
        loadUserDetailsContent()
    }

    private fun loadUserDetailsContent() {
        _userDetailsViewState.value = UserDetailsViewState.Loading

        val selectedUserId = savedStateHandle.toRoute<UserDetails>().userId

        _userDetailsViewState.value =
            UserDetailsViewState.Success(user = userRepository.retrieveUsers().first {
                it.id == selectedUserId
            })
    }
}