package com.gp.sample_feature.ui.deeplink

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun UserDeepLinkScreen(deepLinkText: String) {
    UserDeepLinkContent(
        deepLinkText = deepLinkText
    )
}

@Composable
fun UserDeepLinkContent(deepLinkText: String) {
    Text(
        text = deepLinkText
    )
}