package com.cj.bunnywallet.feature.createwallet.createpwd.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R

@Composable
fun CreateWalletBioSwitch() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.check_unlock_with_biometrics),
            style = MaterialTheme.typography.bodyLarge,
        )

        Switch(
            checked = true,
            onCheckedChange = {},
            modifier = Modifier.scale(scale = 0.8f),
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewCreateWalletBioSwitch() {
    CreateWalletBioSwitch()
}