package com.gp.sample_feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.gp.sample_feature.ui.constants.UriConstants
import com.gp.sample_feature.ui.deeplink.UserDeepLinkScreen
import com.gp.sample_feature.ui.details.UserDetailsScreen
import com.gp.sample_feature.ui.home.UserHomeScreen
import kotlinx.serialization.Serializable

@Serializable
object UserHome

@Serializable
data class UserDetails(val userId: String)

@Serializable
data class UserDeepLink(val testParam: String)

@Composable
fun UserNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(navController = navController, startDestination = UserHome, modifier = modifier) {
        composable<UserHome> {
            UserHomeScreen(navigateToUserDetails = { id ->
                navController.navigate(UserDetails(id))
            })
        }

        composable<UserDetails> {
            UserDetailsScreen()
        }

        composable<UserDeepLink>(
            deepLinks = listOf(
                navDeepLink<UserDeepLink>(basePath = UriConstants.BASE_DEEP_LINK_URI)
            )
        ) {
            val deepLinkText = it.toRoute<UserDeepLink>().testParam
            UserDeepLinkScreen(deepLinkText = deepLinkText)
        }
    }
}

