package com.gp.sample_feature.viewstate

sealed interface UserDeepLinkViewState {
    /**
     * Initial state when the screen is first shown
     */
    data object Initial : UserDeepLinkViewState

    /**
     * Loading state
     */
    data object Loading : UserDeepLinkViewState

    /**
     * User data has been loaded
     */
    data class Success(val deepLinkText: String) : UserDeepLinkViewState
}