package com.gp.sample_feature.viewstate

import com.gp.sample_feature.domain.model.User

sealed interface UserHomeViewState {

    /**
     * Initial state when the user home screen is first shown
     */
    data object Initial : UserHomeViewState

    /**
     * Loading state
     */
    data object Loading : UserHomeViewState

    /**
     * User data has been loaded
     */
    data class Success(val users: List<User>) : UserHomeViewState

    /**
     * Error state, if an error was received when loading the data
     */
    data class Error(val errorMessage: String? = null) : UserHomeViewState
}