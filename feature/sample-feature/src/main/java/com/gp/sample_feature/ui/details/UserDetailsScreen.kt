package com.gp.sample_feature.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gp.sample_feature.R
import com.gp.sample_feature.domain.model.User
import com.gp.sample_feature.ui.constants.Dimens
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
    Card(
        modifier = Modifier.padding(horizontal = Dimens.padding_8, vertical = Dimens.padding_4),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.padding_16, end = Dimens.padding_16, top = Dimens.padding_16)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Column {
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

                    Spacer(modifier = Modifier.height(Dimens.padding_8))
                }
            }
        }

        Column(
            modifier = Modifier.padding(Dimens.padding_16)
        ) {
            Text(
                text = user.description,
                fontSize = Dimens.font_size_14,
                color = Color.Black
            )
        }
    }
}