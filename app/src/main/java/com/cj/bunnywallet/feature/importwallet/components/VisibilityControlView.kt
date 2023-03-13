package com.cj.bunnywallet.feature.importwallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cj.bunnywallet.R

@Composable
fun VisibilityControlView(
    title: String,
    isVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        IconButton(onClick = onVisibilityChange) {
            val icon = if (isVisible) {
                R.drawable.ic_visibility
            } else {
                R.drawable.ic_visibility_off
            }
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}