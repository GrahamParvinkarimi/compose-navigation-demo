package com.gp.sample_feature.viewstate

import com.gp.sample_feature.domain.model.User

sealed interface UserDetailsViewState {
    /**
     * Initial state when the user details screen is first shown
     */
    data object Initial : UserDetailsViewState

    /**
     * Loading state
     */
    data object Loading : UserDetailsViewState

    /**
     * User data has been loaded
     */
    data class Success(val user: User) : UserDetailsViewState
}