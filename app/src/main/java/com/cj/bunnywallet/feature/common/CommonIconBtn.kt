package com.cj.bunnywallet.feature.common

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun CommonIconBtn(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.secondary,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = iconModifier,
            tint = tint
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewCommonIconBtn() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CommonIconBtn(
                icon = R.drawable.ic_cruelty_free,
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = CircleShape
                ),
                onClick = {}
            )
        }
    }
}