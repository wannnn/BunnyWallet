package com.cj.bunnywallet.ui.customview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn

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

        VisibilityIconBtn(isVisible, onVisibilityChange)
    }
}

@Composable
fun VisibilityIconBtn(
    isVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    val icon = if (isVisible) {
        R.drawable.ic_visibility
    } else {
        R.drawable.ic_visibility_off
    }
    CommonIconBtn(
        icon = icon,
        onClick = onVisibilityChange
    )
}