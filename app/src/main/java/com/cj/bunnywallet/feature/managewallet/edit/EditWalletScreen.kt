package com.cj.bunnywallet.feature.managewallet.edit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun EditWalletScreen() {

    Box {
        Text(text = "EditWalletScreen")
    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewEditWalletScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            EditWalletScreen()
        }
    }
}