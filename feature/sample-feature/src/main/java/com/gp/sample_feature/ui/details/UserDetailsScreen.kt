package com.gp.sample_feature.ui.details

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.gp.sample_feature.domain.model.User
import com.gp.sample_feature.viewstate.UserDetailsViewState
import com.gp.sample_feature.vm.UserDetailsViewModel

@Composable
fun UserDetailsScreen(userDetailsViewModel: UserDetailsViewModel = hiltViewModel()) {
    val userDetailsViewState by userDetailsViewModel.userDetailsViewState.collectAsState()

    when (userDetailsViewState) {
        UserDetailsViewState.Initial, UserDetailsViewState.Loading -> {
            CircularProgressIndicator()
        }
        is UserDetailsViewState.Success -> {
            UserDetailsContent(
                user = (userDetailsViewState as UserDetailsViewState.Success).user
            )
        }
    }
}

@Composable
fun UserDetailsContent(user: User) {
    Text(
        text = user.username
    )
}