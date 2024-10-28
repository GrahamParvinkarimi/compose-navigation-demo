package com.gp.sample_feature.ui.deeplink

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gp.sample_feature.R

@Composable
fun UserDeepLinkScreen(deepLinkText: String) {
    UserDeepLinkContent(
        deepLinkText = deepLinkText
    )
}

@Composable
fun UserDeepLinkContent(deepLinkText: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.deep_link_screen_text, deepLinkText)
        )
    }
}