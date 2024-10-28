package com.gp.sample_feature.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.gp.sample_feature.R
import com.gp.sample_feature.data.util.DateUtils
import com.gp.sample_feature.domain.model.User
import com.gp.sample_feature.ui.constants.Dimens
import com.gp.sample_feature.viewstate.UserHomeViewState
import com.gp.sample_feature.vm.UserHomeViewModel

@Composable
fun UserHomeScreen(
    userHomeViewModel: UserHomeViewModel = hiltViewModel(),
    navigateToUserDetails: (String) -> Unit
) {
    val userHomeViewState by userHomeViewModel.userHomeViewState.collectAsState()

    when (userHomeViewState) {
        UserHomeViewState.Initial, UserHomeViewState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is UserHomeViewState.Success -> {
            UserHome(
                userHomeViewState = userHomeViewState as UserHomeViewState.Success,
                navigateToUserDetails = navigateToUserDetails
            )
        }

        is UserHomeViewState.Error -> {
            RetryTombstone(
                errorMessage = stringResource(R.string.default_network_error_message),
                onRetry = { userHomeViewModel.loadUserHomeContent() }
            )
        }
    }
}

@Composable
fun UserHome(
    userHomeViewState: UserHomeViewState.Success,
    navigateToUserDetails: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = Dimens.padding_8)
    ) {
        itemsIndexed(
            userHomeViewState.users
        ) { _, user ->
            UserListItemCard(
                user = user,
                navigateToUserDetails = { navigateToUserDetails(user.id) }
            )
        }
    }
}

@Composable
fun UserListItemCard(
    user: User,
    navigateToUserDetails: () -> Unit
) {
    Card(
        modifier = Modifier.padding(horizontal = Dimens.padding_16, vertical = Dimens.padding_4),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding_16)
                .clickable {
                    navigateToUserDetails()
                }
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.padding(top = Dimens.padding_4)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(R.string.user_icon_content_description)
                    )
                }

                Spacer(modifier = Modifier.width(Dimens.padding_16))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = user.username,
                            fontSize = Dimens.font_size_14,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = DateUtils.parseAndFormatTimestamp(user.email),
                            fontSize = Dimens.font_size_12,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimens.padding_8))
                }
            }

            Row {
                Text(
                    text = user.phoneNumber,
                    fontSize = Dimens.font_size_14,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun RetryTombstone(
    errorMessage: String,
    onRetry: () -> Unit, // Retry action callback
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.padding_16),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface // Error background color
        ),
        shape = RoundedCornerShape(Dimens.padding_8), // Rounded corners for the card
        elevation = CardDefaults.cardElevation(Dimens.padding_4)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding_16),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Error icon or any decorative icon if needed
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = stringResource(R.string.error_icon_content_description),
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.size(Dimens.size_48)
            )

            Spacer(modifier = Modifier.height(Dimens.padding_8))

            // Error message
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = Dimens.padding_16)
            )

            Spacer(modifier = Modifier.height(Dimens.padding_16))

            // Retry Button
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = stringResource(R.string.retry_text))
            }
        }
    }
}