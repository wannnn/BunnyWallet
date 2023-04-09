package com.cj.bunnywallet.feature.managecrypto.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.NoRippleInteractionSource

@Composable
fun CustomCryptoView(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
                onClick = { onClick.invoke() }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.custom_crypto))

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = null,
            modifier = Modifier.size(15.dp)
        )
    }
}